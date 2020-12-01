package io.pandora.mall.module.security;

import io.pandora.mall.domian.system.User;
import io.pandora.mall.exception.BadRequestException;
import io.pandora.mall.exception.TokenException;
import io.pandora.mall.mapper.system.PermissionMapper;
import io.pandora.mall.mapper.system.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author Created by John on 2020/9/10
 */
@Component
public class MyUserDetailsService implements UserDetailsService {

    // 多用途常量 值=1
    private final static int PERMISSION_SIZE = 1;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        User user = userMapper.findUserByAccount(account);
        if (null == user){
            throw new UsernameNotFoundException("账号:" + account +"不存在");
        }else {
            if (user.getEnable() == PERMISSION_SIZE){
                throw new BadRequestException("该账号已被禁用请联系管理员");
            }
            String permission = queryUserPermission(user.getId());
            return new MyUserDetails(user.getId(),user.getAccount(),user.getPassword(), AuthorityUtils.commaSeparatedStringToAuthorityList(permission));
        }
    }

    /**
     * 查询登录账号权限
     * @param userId 账号ID
     * @return       权限字符串
     */
    private String queryUserPermission(Integer userId){
        List<Map<String, String>> permissions = permissionMapper.findPermissionByUserId(userId);
        if (permissions.isEmpty()) {
            throw new TokenException("该账号未有访问权限");
        }
        if (permissions.size() <= PERMISSION_SIZE){
            return permissions.get(0).get("code");
        }
        int i = 0;
        StringBuilder builder = new StringBuilder();
        for (Map<String,String> permission:permissions) {
            builder.append(permission.get("code"));
            if (i < permissions.size()-PERMISSION_SIZE){
                builder.append(",");
            }
            i++;
        }
        return builder.toString();
    }

    private static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    public static String encodePassword(String password){
        return bCryptPasswordEncoder.encode(password);
    }

    public static void main(String[] args) {
        String password = "123456";
        String pwd = encodePassword(password);
        System.out.println(pwd);
    }
}
