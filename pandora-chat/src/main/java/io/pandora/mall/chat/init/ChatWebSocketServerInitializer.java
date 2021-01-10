package io.pandora.mall.chat.init;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;
import io.pandora.mall.chat.websocket.ChatHandler;
import io.pandora.mall.chat.websocket.HeartBeatHandler;

/**
 * 配置从线程池的助手初始化类
 *
 * @author Created by mr_zhou on 2021/1/6
 */
public class ChatWebSocketServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        // 1.获取 pipeline
        ChannelPipeline pipeline = channel.pipeline();

        // 2.  配置handle
        // 2.1 WebSocket基于HTTP协议，需要HTTP编码解码工具
        pipeline.addLast("HttpServerCodec",new HttpServerCodec());

        // 2.2 对于大数据流的支持
        pipeline.addLast(new ChunkedWriteHandler());

        // 2.3 对于HTTP的消息进行聚合，聚合成FullHttpRequest或者FullHttpResponse，几乎所有的Netty都需要此handle。
        pipeline.addLast(new HttpObjectAggregator(1024 * 64));
        // ====================== 以上是对HTTP的支持 ====================== //

        // ====================== 以下是对心跳的支持 ====================== //
        int n = 10;
        pipeline.addLast(new IdleStateHandler(40,50,60 * n));
        pipeline.addLast(new HeartBeatHandler());
        // ====================== 以上是对WebSocket的支持 ====================== //

        /**
         * WebSocket 服务器处理协议，用于指定用户访问的路由：/ws
         * 同时该 handler 会自动处理一些复杂的事情，如握手动作，handshaking(close + ping + pong)
         * ping + pong 组成心跳
         */
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
        pipeline.addLast(new ChatHandler());
    }

}
