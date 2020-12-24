package io.pandora.mall.constant;

import org.springframework.stereotype.Component;

/**
 * @author Created by John on 2020/9/10
 */
@Component
public class Constant {

    /** 系统配置相关 */
    public static final String USER_REGISTER_AWARD_INTEGRAL  = "user_register_award_integral";

    // 用于魔鬼值
    public static final int ZERO = 0;
    public static final int ONE = 1;
    public static final int two = 2;
    public static final int three = 3;

    // token
    public static final String ACCESS_TOKEN = "accessToken";
    public static final long ACCESS_TOKEN_EXPIRE_TIME = 7200000;

    /** redis-OK */
    public final static String OK = "OK";
    public final static String LOCK = "common_lock:lock_";
    /** redis过期时间，以秒为单位，一分钟 */
    public final static int EXPIRE_MINUTE = 60;
    /** redis过期时间，以秒为单位，一小时 */
    public final static int EXPIRE_HOUR = 60 * 60;
    /** redis过期时间，以秒为单位，三小时 */
    public final static int EXPIRE_THREE_HOUR = 60 * 60 * 3;
    /** redis过期时间，以秒为单位，一天 */
    public final static int EXPIRE_DAY = 60 * 60 * 24;

    public static class LoginType {
        public static final int app  = 0;
        public static final int applet  = 1;
        public static final int pc  = 2;
    }
}
