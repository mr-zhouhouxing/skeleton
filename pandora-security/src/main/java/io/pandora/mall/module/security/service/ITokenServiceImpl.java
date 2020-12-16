package io.pandora.mall.module.security.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.pandora.mall.base.service.impl.BaseServiceImpl;
import io.pandora.mall.constant.Constant;
import io.pandora.mall.domian.system.Token;
import io.pandora.mall.exception.TokenException;
import io.pandora.mall.mapper.system.TokenMapper;
import io.pandora.mall.util.DateUtils;
import io.pandora.mall.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author Created by John on 2020/10/16
 */
@Service
public class ITokenServiceImpl extends BaseServiceImpl<TokenMapper, Token> implements ITokenService {

    @Value("${jwt.secret}")
    private String secretStr;

    @Autowired
    private TokenMapper tokenMapper;

    @Override
    public Token findByToken(String tokenStr) {
        if (StringUtils.isEmpty(tokenStr)) {
            return null;
        }
        List<Token> list = tokenMapper.selectList(new QueryWrapper<Token>().eq("token",tokenStr));
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public Token findByToken(Long userId) {
        List<Token> list = tokenMapper.selectList(new QueryWrapper<Token>().eq("user_id",userId));
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public void refreshToken(String token) {
        Token bean = this.findByToken(token);
        if (bean == null || !DateUtils.belongCalendar(new Date(),bean.getExpireTime())){
            throw new TokenException("This AccessToken Expire");
        }
        Date now = new Date();
        bean.setUpdateTime(now);
        bean.setExpireTime(new Date(now.getTime() + Constant.ACCESS_TOKEN_EXPIRE_TIME));
        this.update(bean);
    }

    @Override
    public void insert(Token token) throws TokenException {
        try {
            token.setCreateTime(new Date());
            tokenMapper.insert(token);
        }catch (Exception e){
            LOGGER.error("【Token】-> 保存Token异常:{}",e);
            throw new TokenException("保存Token异常");
        }
    }

    /**
     * @param id        兼容 AppId 和 userId
     * @param secret    密钥
     * @return
     * @throws TokenException
     */
    @Override
    public String createToken(Long id,String secret) throws TokenException {
        // 判空
        if (StringUtils.isEmpty(id)) throw new TokenException("ID cannot be empty");
        if (StringUtils.isEmpty(secret)) secret = secretStr;
        // 查询 appId 及 secret 是否正确

        // 生成token
        String token = newToken(id, secret);
        if (StringUtils.isEmpty(token)){
            LOGGER.error("【Token】-> 生成Token失败:{}",token);
        }
        Date now = new Date();
        Date expireTime = new Date(now.getTime() + Constant.ACCESS_TOKEN_EXPIRE_TIME);

        Token bean = new Token();
        bean.setUserId(id);
        bean.setToken(token);
        bean.setCreateTime(now);
        bean.setExpireTime(expireTime);
        this.insert(bean);
        return token;
    }

    @Override
    public void update(Token token) throws TokenException {
        try {
            tokenMapper.updateById(token);
        }catch (Exception e){
            LOGGER.error("【Token】-> 更新Token异常:{}",e);
            throw new TokenException("更新Token异常");
        }
    }

    private String newToken(Long appId,String secret){
        String token = UUID.randomUUID().toString().replaceAll("-", "");
        LOGGER.info("【Token】-> ID:{},密钥:{},生成:{}",appId,secret,token);
        return token;
    }
}
