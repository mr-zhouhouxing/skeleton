package io.pandora.mall.push.online.websocket;

import com.alibaba.fastjson.JSONObject;
import io.pandora.mall.constant.Constant;
import io.pandora.mall.push.enume.HeartMessage;
import io.pandora.mall.push.enume.WSMsgType;
import io.pandora.mall.push.online.config.WebSocketProperties;
import io.pandora.mall.push.online.convert.MessageDecoder;
import io.pandora.mall.push.online.convert.MessageEncoder;
import io.pandora.mall.redis.util.JedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @version v1.0
 * @author Created by John on 2019/8/12
 */
@Slf4j
@Component
@ServerEndpoint(value = "/ws/{clientId}", encoders = MessageEncoder.class, decoders = MessageDecoder.class)
public class WebSocketServer {

    private String fromId;
    private Thread localThread;
    @Autowired
    private WebSocketProperties webSocketProperties;
    // 连接对象
    private final static ConcurrentHashMap<String, Session> wsSessionMap = new ConcurrentHashMap<>();
    // 连接心跳对象时间
    private final static ConcurrentHashMap<String, LocalDateTime> heartbeatKeepTimeMap = new ConcurrentHashMap<>();

    public WebSocketServer() {}

    @OnOpen
    public void onOpen(@PathParam("clientId") String clientId, Session session) {
        this.fromId = clientId;

        heartbeatKeepTimeMap.put(Constant.WS_CLIENT_ + clientId, LocalDateTime.now());
        Session connectedSession = wsSessionMap.get(Constant.WS_CLIENT_ + clientId);

        try {
            if (!Objects.isNull(connectedSession)) connectedSession.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connectedSession = null;
        }

        wsSessionMap.put(Constant.WS_CLIENT_ + clientId, session);

        log.info("[WebSocket] clientId={}-已连接WS服务器,当前客户端数量为:{}",clientId,wsSessionMap.size());
    }

    @OnMessage
    public void onMessage(@PathParam("clientId") String clientId, Session session, String message) {
        HeartMessage wsMessage = null;

        try {
            wsMessage = JSONObject.parseObject(message, HeartMessage.class);
        } catch (Exception e) {
            log.error("[WebSocket] clientId={},解析客户端消息失败，错误信息:{}" , e.getMessage());
            return;
        }

        switch (WSMsgType.contains(wsMessage.getType())) {
            case WS_MSG_HEART:
                log.info("[WebSocket] 收到 clientId={}-心跳消息：{}", clientId, message);
                handleHeartbeat(wsMessage, session);
                break;
            case WS_MSG_ACK:
                log.info("[WebSocket] 收到 clientId={}-ACK消息：{}", clientId, message);
                sendAckMessage(session, message);
                break;
            default:
                log.info("[WebSocket] 匹配不到clientId={}-消息类型,信息为:{}", clientId, message);
                break;
        }
    }

    @OnClose
    public void onClose() {
        String clientId = this.fromId;

        Session mapSession = wsSessionMap.get(Constant.WS_CLIENT_ + clientId);
        if (mapSession == null) return;

        heartbeatKeepTimeMap.remove(Constant.WS_CLIENT_ + clientId);
        wsSessionMap.remove(Constant.WS_CLIENT_ + clientId);
        JedisUtil.delKey(Constant.USER_ONLINE_LIST + clientId);

        try {
            mapSession.close();
        } catch (IOException e) {
            log.error("[WebSocket] clientId={}-关闭链接出现异常:{}",clientId , e.getMessage());
        }
        log.info("[WebSocket] clientId={}-关闭链接、sessionSize={}", clientId, wsSessionMap.size());
    }

    @OnError
    public void onError(Throwable error) {
        log.error("[WebSocket] clientId={} - error 信息={} 链接超时或非正常关闭链接", fromId, error.getMessage());
    }

    private void sendAckMessage(Session session, String wsMsgResponse) {
        sendMessage(session, wsMsgResponse);
    }

    // 处理业务消息
    //private void handleRequest(PushVO wsMessage, Session session) { /**处理业务逻辑*/ }

    // 处理心跳消息
    private void handleHeartbeat(HeartMessage wsMessage, Session session) {
        String ackMsg = null;

        wsMessage.setTime(LocalDateTime.now().toString());
        wsMessage.setType(WSMsgType.WS_MSG_ACK.getValue());

        try {
            ackMsg = JSONObject.toJSONString(wsMessage);
            session.getBasicRemote().sendText(ackMsg);

        }catch (IOException e) {
            log.error("[WebSocket] 发送心跳信息失败，错误信息：{}", e.getMessage());
        } catch (Exception e) {
            log.error("[WebSocket] 序列化心跳对象失败，错误信息:{}", e.getMessage());
        }
    }

    private void sendMessage(Session session, String message) {
        try {
            if (!session.isOpen()) {
                return;
            }
            session.getBasicRemote().sendText(message);
        } catch (Exception e) {
            log.error("[WebSocket] 发送消息 TO :{},抛出异常={} ", session.getId(), e.getMessage());
        }
    }

    public static ConcurrentHashMap<String, LocalDateTime> getHeartbeatKeepTimeMap() {
        return heartbeatKeepTimeMap;
    }

    public void checkSessionAliveAndRemove(String clientId, LocalDateTime heartbeatKeepTime, LocalDateTime now) {
        // 时间差 单位毫秒
        Long intervalTime = Duration.between(heartbeatKeepTime, now).toMillis();

        if (intervalTime.compareTo(webSocketProperties.getHeartbeatAllowIntervalTime()) > Constant.ZERO) {
            // 大于心跳允许间隔时间 证明该客户端已死
            heartbeatKeepTimeMap.remove(clientId);

            try {
                wsSessionMap.get(clientId).close();
            } catch (IOException e) {
                log.error("[WebSocket] 检查用户={},心跳 抛出异常:{}", clientId, e.getMessage());
            }

            wsSessionMap.remove(clientId);
            log.info("[WebSocket] 移除用户 = {}...", clientId);
        }
    }

}
