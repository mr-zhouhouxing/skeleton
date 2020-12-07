package io.pandora.mall.constant;

import org.springframework.stereotype.Component;

/**
 * @author Created by John on 2020/9/10
 */
@Component
public class Constant {
    /** 系统配置相关 */
    public static final String MALL_ORDER_PRICE_PROPORTION  = "mall_order_price_proportion";

    // 用于魔鬼值 o
    public static final int ZERO = 0;
    public static final int ONE = 1;
    public static final int two = 2;
    public static final int three = 3;
    public static final int ten_size = 10;
    public static final int one_hundred = 10;

    public static final String ACCESS_TOKEN = "accessToken";
    public static final long NULL_UID = 10000;
    public static final int ROLE_ID = 101;

    /** redis-OK */
    public final static String OK = "OK";
    /** redis过期时间，以秒为单位，一分钟 */
    public final static int EXPIRE_MINUTE = 60;
    /** redis过期时间，以秒为单位，一小时 */
    public final static int EXPIRE_HOUR = 60 * 60;
    /** redis过期时间，以秒为单位，三小时 */
    public final static int EXPIRE_THREE_HOUR = 60 * 60 * 3;
    /** redis过期时间，以秒为单位，一天 */
    public final static int EXPIRE_DAY = 60 * 60 * 24;

    /** 锁 */
    public static final String LOCK = "COMM_LOCK_";

    /** 锁 */
    public static final String PRODUCT_BROWSE = "product:browse:productId_";

    public static final String ONLINE_USER_KEY = "online_list:online_token_";

    /** 推荐商品前缀 */
    public static final String RECOMMEND_PRODUCT_ = "product:recommend_list:user_";

    /** 待支付订单 第三方预支付数据缓存前缀 */
    public static final String ORDER_PAYMENT_DATA = "order:order_payment_data_list:orderSn_";

    // 错误码
    public static class errCode{
        // 前端提示信息 错误码
        public static final int APP_MESSAGE_EXCEPTION = 4000;
        // 参数错误码
        public static final int PARAM_CHECK_FAILED = 4001;
        // 成功状态码
        public static final int OK = 200;
        // accessToken 校验失败码
        public static final int TOKEN_EXCEPTION = 403;
    }

    // 通用状态
    public static class Status{
        // 正常
        public static final int normal = ZERO;
        // 已过期/未上架 等等
        public static final int expired = ONE;
        // 已删除
        public static final int delete = two;
    }

    // 广告状态
    public static class AdvertiseStatus{
        // 等待中
        public final static int waiting = ZERO;
        // 进行中
        public final static int underway = ONE;
        // 已结束
        public final static int over = two;
    }
    /**
     * 轮播位置：1-> app 首页轮播
     */
    public static class AdvertiseType{
        public final static int index = ONE;
    }

    /**
     * Pan账单类型
     */
    public static class PanBillType{
        public final static String MALL_DEDUCTION = "15";
    }

}
