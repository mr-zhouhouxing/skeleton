package io.pandora.mall.chat;

import io.pandora.mall.chat.service.ChatConnectLogService;
import io.pandora.mall.chat.websocket.service.ChatWebSocketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.util.AntPathMatcher;

/**
 * 启动入口
 *
 * @author Created by mr_zhou on 2021/1/6
 */
@SpringBootApplication
@ServletComponentScan
public class ChatApplication implements CommandLineRunner {

    private static Logger logger = LoggerFactory.getLogger(ChatApplication.class);

    @Autowired
    private ChatWebSocketService chatWebSocketService;
    @Autowired
    private ChatConnectLogService chatConnectLogService;

    @Bean
    public AntPathMatcher antPathMatcher(){return new AntPathMatcher();}

    public static void main(String[] args) {
        SpringApplication.run(ChatApplication.class,args);
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("[CHAT] Service startup removes all users...");
        // 全部下线
        chatConnectLogService.removeAll(chatWebSocketService.getInstanceFlag());
    }
}
