package io.pandora.mall.email.model;

/**
 * 1.验证码,2.HTML,3.文本
 *
 * @author Created by mr_zhou on 2020/12/25
 */
public enum  EmailSendType {
    verifyCode(1,"验证码"),HTML(2,"HTML"),TEXT(3,"文本");

    private int type;
    private String name;

    EmailSendType(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static boolean verify(int type){
        for (EmailSendType value : EmailSendType.values()) {
            if (value.type == type) return true;
        }
        return false;
    }
}
