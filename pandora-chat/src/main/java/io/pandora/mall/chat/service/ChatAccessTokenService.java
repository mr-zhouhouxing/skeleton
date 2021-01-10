package io.pandora.mall.chat.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.pandora.mall.chat.config.AppProp;
import io.pandora.mall.domian.system.Token;
import io.pandora.mall.mapper.system.TokenMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Created by mr_zhou on 2021/1/6
 */
@Service
public class ChatAccessTokenService {

    @Autowired
    private AppProp appProp;
    @Autowired
    private TokenMapper tokenMapper;

    /**
     * 信息认证
     *
     * @param clientType
     * @param accessToken
     * @param secret
     * @return
     */
    public Token checkAuth(String clientType , String accessToken , String secret){
        Token token = tokenMapper.selectOne(new QueryWrapper<Token>().eq("token", accessToken));
        return token;
    }


    /**
     * 检查 Token 是否存在
     *
     * @param token
     * @return
     */
    public boolean exists(String token){
        // 测试环境下 使用测试Token
        if (appProp.getActive().equalsIgnoreCase("DEV") && "testToken".equals(token)){
            return true;
        }
        return this.checkAuth("",token,"") != null ? true : false;
    }


}
