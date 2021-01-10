package io.pandora.mall.chat.websocket.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.pandora.mall.chat.init.ChatWebSocketServerInitializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author Created by mr_zhou on 2021/1/7
 */
@Slf4j
@Component
public class ChatWebSocketServer {

    // 静态内部类
    private static class SingletonWebSocketServer {
        static final ChatWebSocketServer instance = new ChatWebSocketServer();
    }

    /**
     * 公开获取的静态方法
     *
     * @return ChatWebSocketServer
     */
    public static ChatWebSocketServer getInstance(){
        return SingletonWebSocketServer.instance;
    }

    private NioEventLoopGroup boss;
    private NioEventLoopGroup worker;
    private ServerBootstrap bootstrap;
    private ChannelFuture channelFuture;

    /**
     * 私有化构造子
     */
    private ChatWebSocketServer() {
        // 1.两个线程组
        boss = new NioEventLoopGroup();
        worker = new NioEventLoopGroup();
        // 2.启动类
        bootstrap = new ServerBootstrap();
        // 3.定义启动的线程组,channel和初始化器
        bootstrap.group(boss,worker).channel(NioServerSocketChannel.class).childHandler(new ChatWebSocketServerInitializer());
    }

    /**
     * 启动Netty服务器
     *
     * @param port
     */
    public void start(int port) {
        this.channelFuture = bootstrap.bind(port);
        log.info("[Netty] WebSocket Server Starting on port:{}......",port);
    }
}
