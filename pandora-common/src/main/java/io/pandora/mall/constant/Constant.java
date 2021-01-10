package io.pandora.mall.constant;

import org.springframework.stereotype.Component;

/**
 * @author Created by John on 2020/9/10
 */
@Component
public class Constant {

    // 验证码 期限 15 分钟
    public final static int VERIFY_CODE_TIME = 15 * 60;
    public static final String VERIFY_CODE = "verify_code:mobile_";

    // 系统管理员默认邮箱
    public static final String SYSTEM_ADMIN_DEFAULT_EMAIL = "2193351252@qq.com";
    public static final String USER_DEFAULT_AVATAR = "http://pandora.io/images/logo.png";
    public static final String USER_DEFAULT_SIGNATURE = "user_default_signature";

    /** 系统配置相关 */
    public static final String SMS_RETRY_NUMBER = "sms_retry_number";
    public static final String SYSTEM_ADMIN_EMAIL = "system_admin_email";
    public static final String USER_RANDOM_AVATAR  = "user_random_avatar";
    public static final String SYSTEM_SMS_NUMBER_WARN = "system_sms_number_warn";
    public static final String USER_REGISTER_AWARD_INTEGRAL  = "user_register_award_integral";

    // 用于魔鬼值
    public static final int ZERO = 0;
    public static final int ONE = 1;
    public static final int TWO = 2;
    public static final int THREE = 3;

    // token
    public static final String ACCESS_TOKEN = "accessToken";
    public static final long ACCESS_TOKEN_EXPIRE_TIME = 7200000;

    /** ws 对象前缀 */
    public static final String WS_CLIENT_ = "ws_";

    // Redis
    public final static String LOCK = "common_lock:lock_";
    public final static String OK = "OK";
    public final static int EXPIRE_MINUTE = 60;     // 以秒为单位，60S
    public final static int EXPIRE_HOUR = 60 * 60;
    public final static int EXPIRE_THREE_HOUR = 60 * 60 * 3;
    public final static int EXPIRE_DAY = 60 * 60 * 24;
    public final static int EXPIRE_MONTH = 60 * 60 * 24 * 30;

    // 离线推送客户端ID
    public final static String USER_PUSH_CLIENT = "user:users_push_client:user_";
    // 用户在线列表
    public final static String USER_ONLINE_LIST = "user:users_online:user_";
    // 用户Token 列表
    public final static String USER_TOKEN_LIST = "user:users_token:user_";
    // 用户资料缓存
    public final static String USER_INFO_PREFIX = "user_info_list:user_info_";

    /**
     * 用户登录类型: APP 小程序 PC端
     */
    public static class LoginType {
        public static final int APP  = 0;
        public static final int APPLET  = 1;
        public static final int PC  = 2;
    }

    /**
     * 积分来源
     */
    public static class IntegralSource {
        // 注册赠送
        public static final int register  = 1;
        // 邀请好友
        public static final int invite_user  = 2;
    }

    public static class File {
        // OSS 后缀
        public static final String suffix = "?x-oss-process=image/resize,w_700/format,jpg/quality,q_90";

        /** 用户头像图片 *//** 用户头像图片 */
        public static final int USER_INFO_IMAGE = 1;
        public static final String USER_INFO_VALUE = "user_info/";

        /** 用户聊天图片 */
        public static final int USER_CHAT_MESSAGE = 2;
        public static final String USER_CHAT_MESSAGE_VALUE = "user_chat/";

        /** 用户日志图片 */
        public static final int USER_DYNAMIC = 3;
        public static final String USER_DYNAMIC_VALUE = "user_dynamic/";

        /** 酒吧 OR 公益图片 */
        public static final int BAR_OR_BENEFIT = 4;
        public static final String BAR_OR_BENEFIT_VALUE = "bar_benefit/";

        /** APP 活动图片 */
        public static final int APP_ACTIVE_IMAGE = 5;
        public static final String APP_ACTIVE_IMAGE_VALUE = "app_image/";

    }
}
