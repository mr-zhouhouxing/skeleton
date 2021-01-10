package io.pandora.mall.chat;

import io.pandora.mall.chat.config.AppProp;
import io.pandora.mall.chat.websocket.server.ChatWebSocketServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * 当SpringBoot启动后,自动加载此类启动Netty
 *
 * @author Created by mr_zhou on 2021/1/7
 */
@Slf4j
@Component
public class ChatNettyBoote implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private AppProp app;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (event.getApplicationContext().getParent() == null){
            try {
                // 事件获得上下文对象化后启动服务器
                ChatWebSocketServer.getInstance().start(app.getPort());
            }catch (Exception e){
                log.error("[Netty] 初始化启动失败,异常信息:{},异常栈信息:{}",e.getMessage(),e);
            }
        }
    }

}
