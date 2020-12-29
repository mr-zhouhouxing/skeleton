package io.pandora.mall.module.social.impl;

import io.pandora.mall.base.service.impl.BaseServiceImpl;
import io.pandora.mall.constant.Constant;
import io.pandora.mall.domian.social.SocialUserIntegral;
import io.pandora.mall.domian.social.SocialUserIntegralConsume;
import io.pandora.mall.domian.social.SocialUserIntegralExample;
import io.pandora.mall.domian.social.SocialUserIntegralSource;
import io.pandora.mall.mapper.social.SocialUserIntegralConsumeMapper;
import io.pandora.mall.mapper.social.SocialUserIntegralMapper;
import io.pandora.mall.mapper.social.SocialUserIntegralSourceMapper;
import io.pandora.mall.module.social.SocialUserIntegralService;
import io.pandora.mall.module.social.processor.integral.UserIntegralProcessor;
import io.pandora.mall.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author Created by mr_zhou on 2020/12/29
 */
@Slf4j
@Service
public class SocialUserIntegralServiceImpl extends BaseServiceImpl<SocialUserIntegralMapper,SocialUserIntegral>
        implements SocialUserIntegralService {

    @Autowired
    private SocialUserIntegralMapper userIntegralMapper;
    @Autowired
    private SocialUserIntegralSourceMapper userIntegralSourceMapper;
    @Autowired
    private SocialUserIntegralConsumeMapper userIntegralConsumeMapper;

    @Override
    public void initUserAccount(Long userId, BigDecimal number, int resource) {
        SocialUserIntegralExample example = new SocialUserIntegralExample();
        example.createCriteria().andUserIdEqualTo(userId);
        List<SocialUserIntegral> userIntegrals = userIntegralMapper.selectByExample(example);

        if (StringUtils.isEmpty(userIntegrals)){
            int value = number.intValue();

            SocialUserIntegral userIntegral = new SocialUserIntegral();
            userIntegral.setUserId(userId);
            userIntegral.setAccumulate(new BigDecimal(0));
            userIntegral.setConsume(new BigDecimal(0));
            userIntegral.setFreeze(new BigDecimal(0));
            userIntegral.setScoreMemo("扩展字段");
            userIntegral.setCreateTime(new Date());
            userIntegral.setUpdateTime(new Date());
            int insert = userIntegralMapper.insert(userIntegral);

            if (insert > Constant.ZERO && value > Constant.ZERO){
                this.addUserIntegral(UserIntegralProcessor
                        .builder().number(new BigDecimal(value)).source(resource).sourceMemo("注册赠送").userId(userId)
                        .build()
                );
            }
        }
    }

    @Override
    @Transactional
    public void addUserIntegral(UserIntegralProcessor processor) {
        BigDecimal number = processor.getNumber();
        if (number.intValue() > Constant.ZERO){
            // 增加积分余额
            userIntegralMapper.increaseUserIntegral(processor.getUserId(),number);

            // 增加积分明细
            SocialUserIntegralSource integralSource = new SocialUserIntegralSource();
            integralSource.setUserId(processor.getUserId());
            integralSource.setNumber(number);
            integralSource.setSource(processor.getSource());
            integralSource.setSourceMemo(processor.getSourceMemo());
            integralSource.setCreateTime(new Date());
            userIntegralSourceMapper.insert(integralSource);
        }
    }

    @Override
    @Transactional
    public void decreaseUserIntegral(UserIntegralProcessor processor) {
        BigDecimal number = processor.getNumber();
        if (number.intValue() > Constant.ZERO){
            // 扣除积分
            userIntegralMapper.decreaseUserIntegral(processor.getUserId(),number);

            // 扣除积分明细
            SocialUserIntegralConsume consume = new SocialUserIntegralConsume();
            consume.setUserId(processor.getUserId());
            consume.setNumber(number);
            consume.setExpendSource(processor.getSource());
            consume.setExpendMemo(processor.getSourceMemo());
            consume.setCreateTime(new Date());
            userIntegralConsumeMapper.insert(consume);
        }
    }
}
