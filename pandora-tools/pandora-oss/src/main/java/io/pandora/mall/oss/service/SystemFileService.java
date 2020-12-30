package io.pandora.mall.oss.service;

import io.pandora.mall.base.service.BaseService;
import io.pandora.mall.domian.system.FileDO;

/**
 * @author Created by mr_zhou on 2020/12/30
 */
public interface SystemFileService extends BaseService<FileDO> {

    String upload(byte[] uploadBytes, String fileName, int type);

}
