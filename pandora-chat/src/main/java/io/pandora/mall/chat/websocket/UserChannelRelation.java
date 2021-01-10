package io.pandora.mall.chat.websocket;

import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Created by mr_zhou on 2021/1/6
 */
@Slf4j
public class UserChannelRelation {

    /** 定义静态变量 存储器 */
    private static final ConcurrentHashMap<Long, Channel> manager = new ConcurrentHashMap<>();

    public static void put(Long sessionId , Channel channel){
        manager.put(sessionId,channel);
    }

    public static Optional<Channel> get(String sessionId){
        return Optional.ofNullable(manager.get(sessionId));
    }

    public static void outAllChannel(){
        log.info("===============> 开始输出 <===============");

        for (Map.Entry<Long,Channel> channel : manager.entrySet()) {
            log.info("[Netty] user={},channelId={}",channel.getKey(),channel.getValue().id().asShortText());
        }

        log.info("===============> 输出结束 <===============");
    }
}
