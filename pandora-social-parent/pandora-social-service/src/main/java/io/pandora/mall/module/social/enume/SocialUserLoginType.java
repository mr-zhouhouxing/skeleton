package io.pandora.mall.module.social.enume;

/**
 * 登录类型枚举(可扩展)
 *
 * @author Created by mr_zhou on 2020/12/24
 */
public enum SocialUserLoginType {
    PASSWORD_TO_LOGIN(1000,"accountAndPasswordStrategy","账号密码登录"),
    VERIFY_CODE_TO_LOGIN(1001,"verifyCodeStrategy","验证码登录"),
    WeCHAT_TO_LOGIN(1002,"weChatLoginStrategy","微信登录");

    private int type;
    private String bean;
    private String name;

    SocialUserLoginType(int type, String bean, String name) {
        this.type = type;
        this.bean = bean;
        this.name = name;
    }

    public static String getBean(Integer type){
        for (SocialUserLoginType value : SocialUserLoginType.values()) {
            if (type == value.type) return value.bean;
        }
        return PASSWORD_TO_LOGIN.bean;
    }

    public int getType() { return type; }

    public String getBean() {
        return bean;
    }

    public String getName() {
        return name;
    }

}
