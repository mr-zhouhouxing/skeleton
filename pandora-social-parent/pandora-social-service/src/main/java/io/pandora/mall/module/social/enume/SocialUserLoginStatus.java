package io.pandora.mall.module.social.enume;

/**
 * @author Created by mr_zhou on 2020/12/24
 */
public enum SocialUserLoginStatus {
    normal(0,"正常"),forbidden(1,"该账号已禁用"),cancelled(2,"该账号已注销");

    private int status;
    private String message;

    SocialUserLoginStatus(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() { return message; }

}
