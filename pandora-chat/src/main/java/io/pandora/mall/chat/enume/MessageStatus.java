package io.pandora.mall.chat.enume;

/**
 * @author Created by mr_zhou on 2021/1/6
 */
public enum  MessageStatus {
    TOKEN_VERIFY_ERROR(5000,"Token authentication failed"),CONNECT_OK(2000,"Connection successful"),
    TOKEN_INVALID(5001,"Invalid token");

    private final Integer value;
    private final String reasonPhrase;

    MessageStatus(Integer value, String reasonPhrase) {
        this.value = value;
        this.reasonPhrase = reasonPhrase;
    }

    public Integer getValue() {
        return value;
    }

    public String getReasonPhrase() {
        return reasonPhrase;
    }
}
