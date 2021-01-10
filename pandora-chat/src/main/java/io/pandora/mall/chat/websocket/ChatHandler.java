package io.pandora.mall.chat.websocket;

import com.alibaba.fastjson.JSONObject;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
import io.pandora.mall.chat.enume.MessageActionEnum;
import io.pandora.mall.chat.enume.MessageStatus;
import io.pandora.mall.chat.pojo.DataContent;
import io.pandora.mall.chat.pojo.request.WebSocketRegister;
import io.pandora.mall.chat.pojo.response.MsgResponseVo;
import io.pandora.mall.chat.service.ChatAccessTokenService;
import io.pandora.mall.chat.util.SpringUtils;
import io.pandora.mall.chat.websocket.service.ChatWebSocketService;
import io.pandora.mall.domian.system.Token;
import io.pandora.mall.exception.TokenException;
import io.pandora.mall.util.DateUtils;
import io.pandora.mall.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

import java.util.Date;

/**
 * 自定义 Handler,继承自简单频道入站处理程序,范围为文本套接字Frame。
 * WebSocket 间通过 Frame 进行数据传递和发送。
 * 此版本为User与Channel绑定的版本，消息会定向发送和接收到指定User的channel中。
 *
 * @author Created by mr_zhou on 2021/1/6
 */
@Slf4j
public class ChatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    /**
     * 定义channel集合，管理channel，传入全局事件处理器
     */
    public static final ChannelGroup users = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    /**
     * 打开新连接
     * 获取到客户端的 channel 并且放到 ChannelGroup中去管理
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        users.add(ctx.channel());

        // 设备连接记录
        ChatWebSocketService webSocketService = SpringUtils.getBean(ChatWebSocketService.class);
        webSocketService.connected(ctx.channel().id().asShortText());

        log.info("[Netty] 服务端接入新连接,客户端 ChannelId={},当前总连接数:{}",ctx.channel().id(),users.size());
    }

    /**
     * 信道的消息处理机制，该方法处理一次，故需要同时对所有的客户端进行操作(ChannelGroup)
     *
     * @param ctx  上下文
     * @param msg  文本消息
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        // 1.获取客户端传递过来的消息
        String text = msg.text();
        // 2.获取channel
        Channel channel = ctx.channel();

        DataContent content = JSONObject.parseObject(text, DataContent.class);
        Integer action = content.getAction();
        log.info("[Netty] 接收到ChannelId={},的数据为:{},消息类型为:{}",channel.id().asShortText(),text,action);

        if ( action == MessageActionEnum.CONNECT.getType() ){
            // 连接注册
            registerConnect(content,channel);
        }else if (action == MessageActionEnum.KEEP_ALIVE.getType()){
            // 心跳消息
            keepAlive(content.getJsonObject(),channel);
        }else if (action == MessageActionEnum.CLIENT_ERROR.getType()){
            // 客户端异常
            execClientError(content.getJsonObject(),channel);
        }else if (action == MessageActionEnum.PAGE_CHANGE.getType()){
            // 页面切换
            pageChange(content.getJsonObject(),channel);
        }
    }

    /**
     * 处理器移除时,移除ChannelGroup中的channel
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        // 打印异常的 channel
        String channelId = ctx.channel().id().asShortText();

        log.info("[Netty] 移除客户端 ChannelId={}.....",channelId);
        ChatWebSocketService webSocketService = SpringUtils.getBean(ChatWebSocketService.class);
        webSocketService.offLine(channelId);

        users.remove(ctx.channel());
    }

    /**
     * 发生异常时,关闭连接(channel),随后将channel从ChannelGroup中移除
     *
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        String channelId = ctx.channel().id().asShortText();
        log.error("[Netty] ChannelId={} 出错啦,原因竟然是:{}",channelId,cause.getMessage());

        // 移除
        ctx.channel().close();
        users.remove(ctx.channel());
        ChatWebSocketService webSocketService = SpringUtils.getBean(ChatWebSocketService.class);
        webSocketService.offLine(channelId);
    }

    private void registerConnect(DataContent content, Channel channel) {
        String tokenId = content.getTokenId();
        // 检测 Token
        ChatAccessTokenService tokenService = SpringUtils.getBean(ChatAccessTokenService.class);
        boolean exists = tokenService.exists(tokenId);

        if (exists){
            try {
                Token bean = tokenService.checkAuth("",tokenId,"");
                if (bean == null) throw new TokenException("Netty Token authentication failed");

                log.info("[Netty] 用户【{}】,channelId={},注册成功...",bean.getUserId(),channel.id().asShortText());
                UserChannelRelation.put(bean.getUserId() ,channel);

                String object = content.getJsonObject();
                if (StringUtils.isEmpty(object)) return;

                WebSocketRegister webSocketRegister = JSONObject.parseObject(object, WebSocketRegister.class);

                ChatWebSocketService webSocketService = SpringUtils.getBean(ChatWebSocketService.class);
                webSocketService.registerSuccess(channel.id().asShortText(),tokenId,webSocketRegister);

                String value = JSONObject.toJSONString(
                        MsgResponseVo
                                .builder()
                                .code(MessageStatus.CONNECT_OK.getValue())
                                .message(MessageStatus.CONNECT_OK.getReasonPhrase())
                                .dateTime(DateUtils.format(new Date(), DateUtils.YYYY_MM_DD_HH_MM_SS))
                                .build()
                );
                channel.writeAndFlush(new TextWebSocketFrame(value));
            }catch (Exception e) {
                log.error("[Netty] channelId={},出现异常:{}...",channel.id().asShortText(),e);

                users.remove(channel);
                String value = JSONObject.toJSONString(
                        MsgResponseVo
                                .builder()
                                .code(MessageStatus.TOKEN_INVALID.getValue())
                                .message(MessageStatus.TOKEN_INVALID.getReasonPhrase())
                                .dateTime(DateUtils.format(new Date(), DateUtils.YYYY_MM_DD_HH_MM_SS))
                                .build()
                );
                channel.writeAndFlush(new TextWebSocketFrame(value));
            }
            return;
        }

        log.info("[Netty] channelId={},Token 校验未通过...",channel.id().asShortText());
        users.remove(channel);

        String value = JSONObject.toJSONString(
                MsgResponseVo
                        .builder()
                        .code(MessageStatus.TOKEN_VERIFY_ERROR.getValue())
                        .message(MessageStatus.TOKEN_VERIFY_ERROR.getReasonPhrase())
                        .dateTime(DateUtils.format(new Date(), DateUtils.YYYY_MM_DD_HH_MM_SS))
                        .build()
        );
        channel.writeAndFlush(new TextWebSocketFrame(value));
    }

    private void keepAlive(String content, Channel channel) {
        log.info("[Netty] channelId={},发送心跳包至服务器,内容为:{}",channel.id().asShortText(),content);
    }

    private void execClientError(String content, Channel channel) {
        log.info("[Netty] channelId={},客户端错误处理,消息内容为:{}",channel.id().asShortText(),content);
    }

    private void pageChange(String content, Channel channel) {
        log.info("[Netty] channelId={},进行页面切换,消息内容为:{}",channel.id().asShortText(),content);
    }

}
