package io.pandora.mall.push.offline.service;

import io.pandora.mall.base.service.BaseService;
import io.pandora.mall.domian.system.SystemPushRecord;
import io.pandora.mall.push.offline.dto.PushRequest;
import org.springframework.scheduling.annotation.Async;

/**
 * @author Created by mr_zhou on 2021/1/4
 */
public interface SystemPushService extends BaseService<SystemPushRecord> {

    @Async
    void sendOffLineMessage(PushRequest request);

    @Async
    void cleanClientId(Long userId);

}
