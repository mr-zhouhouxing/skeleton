package io.pandora.mall.chat.enume;

/**
 * @author Created by mr_zhou on 2021/1/6
 */
public enum MessageActionEnum {
    CONNECT(1,"第一次(或重连)初始化连接"),KEEP_ALIVE(2,"客户端保持心跳"),
    CLIENT_ERROR(3,"客户端异常信息反馈"),PAGE_CHANGE(4,"客户端页面切换");

    private final Integer type;
    private final String content;

    MessageActionEnum(Integer type, String content) {
        this.type = type;
        this.content = content;
    }

    public Integer getType() {
        return type;
    }

    public String getContent() {
        return content;
    }
}
