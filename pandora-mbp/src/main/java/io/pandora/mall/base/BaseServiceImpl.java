package io.pandora.mall.base;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Created by mr_zhou on 2020/11/30
 */
public abstract class BaseServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M,T> implements BaseService<T> {

    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());


}
