package io.pandora.mall.base.service;

import io.pandora.mall.domian.system.Config;

/**
 * @author Created by mr_zhou on 2020/12/16
 */
public interface ConfigService extends BaseService<Config> {

    Config getByKey(String key);

    String getValueByKey(String key);

    Integer getValue2Int(String key, Integer defaultValue);

    Long getValue2Long(String key, Long defaultValue);
}
