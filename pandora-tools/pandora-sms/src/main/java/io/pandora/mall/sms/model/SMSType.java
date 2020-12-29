package io.pandora.mall.sms.model;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 1.登录验证,2.注册验证,3.忘记密码
 *
 * @author Created by mr_zhou on 2020/12/25
 */
public enum SMSType {
     login(1, "登录验证"), register(2, "注册验证"),forget_password(3, "忘记密码");

    private int type;
    private String name;

    SMSType(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public static String getName(int type) {
        return Arrays.stream(SMSType.values()).filter(s1 -> s1.type == type)
                .map(SMSType::getName)
                .collect(Collectors.toList())
                .get(0);
    }

    public static boolean verify(int type) {
        for (SMSType sms: SMSType.values()) {
            if (sms.type == type) return true;
        }
        return false;
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
}
