package io.pandora.mall.chat.websocket;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.pandora.mall.chat.util.SpringUtils;
import io.pandora.mall.chat.websocket.service.ChatWebSocketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 用于处理客户端与服务端的心跳，在客户端空闲(如飞行模式)时关闭channel，节省服务器资源。
 *
 * @author Created by mr_zhou on 2021/1/6
 */
@Slf4j
public class HeartBeatHandler extends ChannelInboundHandlerAdapter {

    /**
     * 用户事件触发 处理器
     *
     * @param ctx
     * @param evt
     * @throws Exception
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        // 判断 evt 是否属于 IdleStateEvent，用于触发用户时间，包含读空闲、写空闲、读写空闲。
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            String channelId = ctx.channel().id().asShortText();

            if ( event.state() == IdleState.READER_IDLE ){
                log.info("[Netty] ChannelId={} 进入读空闲,本实例当前连接数:{}",channelId,ChatHandler.users.size());

            }else if (event.state() == IdleState.WRITER_IDLE ){
                log.info("[Netty] ChannelId={} 进入写空闲,本实例当前连接数:{}",channelId,ChatHandler.users.size());

            }else if (event.state() == IdleState.ALL_IDLE ){
                // 关闭 Channel
                Channel channel = ctx.channel();
                channel.close();
                log.info("[Netty] ChannelId={} 进行关闭,关闭后连接数:{}",channelId,ChatHandler.users.size());

                ChatWebSocketService webSocketService = SpringUtils.getBean(ChatWebSocketService.class);
                webSocketService.offLine(channelId);
            }
        }
    }

}
