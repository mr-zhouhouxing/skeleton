package io.pandora.mall.chat.websocket.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.pandora.mall.chat.config.AppProp;
import io.pandora.mall.chat.pojo.request.WebSocketRegister;
import io.pandora.mall.chat.service.ChatClientUserService;
import io.pandora.mall.chat.service.ChatConnectLogService;
import io.pandora.mall.constant.Constant;
import io.pandora.mall.domian.chat.ChatConnectLog;
import io.pandora.mall.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Created by mr_zhou on 2021/1/6
 */
@Slf4j
@Service
public class ChatWebSocketService {

    @Autowired
    private AppProp appProp;
    @Autowired
    private ChatClientUserService chatClientUserService;
    @Autowired
    private ChatConnectLogService chatConnectLogService;

    public void connected(String channelId) {
        chatConnectLogService.save(ChatConnectLog.builder()
                .channelId(channelId)
                .status(Constant.ZERO)
                .token("")
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .instanceFlag("")
                .build());
    }

    public void offLine(String channelId) {
        List<ChatConnectLog> logList = chatConnectLogService.list(new QueryWrapper<ChatConnectLog>()
                .eq("channel_id", channelId));

        if (logList != null && logList.size() > Constant.ZERO){
            logList.forEach( logEntity -> { logEntity.setStatus(Constant.THREE); });
            chatConnectLogService.updateBatchById(logList);
        }
    }

    public void registerSuccess(String channelId, String token, WebSocketRegister register) throws UnknownHostException {
        // 构建连接日志  记录
        ChatConnectLog connectLog = ChatConnectLog.builder()
                .channelId(channelId)
                .status(Constant.ONE)
                .token(token)
                .clientType(register.getClientType())
                .app(register.getApp())
                .user(register.getUser())
                .group(register.getGroup())
                .areaCode(register.getAreaCode())
                .country(StringUtils.isEmpty(register.getCountry()) ? "CN" : register.getCountry())
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .serverPort(appProp.getPort())
                .serverHost(InetAddress.getLocalHost().getHostAddress())
                .instanceFlag(this.getInstanceFlag())
                .build();
        // 保存记录
        chatConnectLogService.save(connectLog);

        chatConnectLogService.remove(new QueryWrapper<ChatConnectLog>().eq("channel_id",channelId).eq("status",0));
    }

    /**
     * 计算实例标识
     * 这个规则可以变化
     * 这个计算规则的编号，往往和部署方式有关
     *
     * @version v1 运行环境的 IP+PORT
     * @return
     * @throws UnknownHostException
     */
    public String getInstanceFlag() throws UnknownHostException{
        return InetAddress.getLocalHost().getHostAddress() + appProp.getPort();
    }
}
