package io.pandora.mall.config;

import io.pandora.mall.util.spring.SecurityUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 权限检测配置
 *
 * @author Created by mr_zhou on 2020/11/30
 */
@Service("el")
public class ElPermissionConfig {

    /**
     * TODO: 检查是否包含权限
     *
     * @param permissions  需要权限
     * @return
     */
    public boolean check(String... permissions){
        List<String> elPermissions = SecurityUtils.getUserDetails().getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        // 如果权限包含 admin 则放开  否则校验是否包含该权限
        return elPermissions.contains("admin") || Arrays.stream(permissions).anyMatch(elPermissions::contains);
    }
}
