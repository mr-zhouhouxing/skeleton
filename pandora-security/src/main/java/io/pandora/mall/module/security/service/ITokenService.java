package io.pandora.mall.module.security.service;

import io.pandora.mall.base.service.BaseService;
import io.pandora.mall.domian.system.Token;
import io.pandora.mall.exception.TokenException;
import io.pandora.mall.pojo.vo.system.TokenVo;

/**
 * @author Created by John on 2020/10/16
 */
public interface ITokenService extends BaseService<Token> {

    Token findByToken(String tokenStr);

    Token findByToken(Long userId);

    void refreshToken(String token);

    void insert(Token token) throws TokenException;

    String createToken(Long id,String secret) throws TokenException;

    TokenVo createToken(Long id);

    void update(Token token) throws TokenException;

    void deleteToken(Long userId);
}
