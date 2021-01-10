package io.pandora.mall.manage;

import io.pandora.mall.module.security.utils.SecurityUtils;
import org.springframework.security.core.userdetails.UserDetails;

public abstract class BaseController {
    
    public static Long getUserId() {
        return SecurityUtils.getUserId();
    }

    public static String getUserName() { return SecurityUtils.getUsername(); }

    public static UserDetails getUser() {
        return SecurityUtils.getUserDetails();
    }

}
