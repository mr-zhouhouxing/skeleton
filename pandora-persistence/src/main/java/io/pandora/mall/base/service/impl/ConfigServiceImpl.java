package io.pandora.mall.base.service.impl;

import io.pandora.mall.base.service.ConfigService;
import io.pandora.mall.domian.system.Config;
import io.pandora.mall.domian.system.ConfigExample;
import io.pandora.mall.mapper.system.ConfigMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Created by mr_zhou on 2020/12/16
 */
@Service
public class ConfigServiceImpl extends BaseServiceImpl<ConfigMapper, Config> implements ConfigService {

    @Autowired
    private ConfigMapper configDao;

    @Override
    public Config getByKey(String key) {
        ConfigExample configExample = new ConfigExample();
        configExample.createCriteria().andKeyEqualTo(key);
        List<Config> list = configDao.selectByExample(configExample);
        if (!list.isEmpty())  return list.get(0);
        return null;
    }

    @Override
    public String getValueByKey(String key) {
        Config config = getByKey(key);
        if (null != config) return config.getValue();
        return null;
    }

    @Override
    public Integer getValue2Int(String key, Integer defaultValue) {
        return Optional.ofNullable(getValueByKey(key)).map(Integer::valueOf).orElse(defaultValue);
    }

    @Override
    public Long getValue2Long(String key, Long defaultValue) {
        return Optional.ofNullable(getValueByKey(key)).map(Long::valueOf).orElse(defaultValue);
    }

}
