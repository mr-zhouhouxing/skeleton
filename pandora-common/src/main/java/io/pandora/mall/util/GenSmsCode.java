package io.pandora.mall.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Created by John on 2020/3/5
 */
public class GenSmsCode {

    private static AtomicInteger orderIdCount = new AtomicInteger();

    private static final SimpleDateFormat ORDER_ID_FORMAT = new SimpleDateFormat("yyyyMMHHmmss");

    public static String genSixVerifyCode() {
        String time = System.nanoTime() + "";
        return time.substring(time.length() - 6);
    }

    public static String genSessionId() {
        return UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }

    public static String genOrderId(String machineNo, String env) {
        int i = orderIdCount.incrementAndGet() % 1000;
        if (i < 1000) i += 1000;
        return env + machineNo + ORDER_ID_FORMAT.format(new Date()) + i;
    }

    public static String genFileName() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static String genUUId() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
