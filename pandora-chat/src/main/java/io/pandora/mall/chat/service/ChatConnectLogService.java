package io.pandora.mall.chat.service;

import io.pandora.mall.base.service.BaseService;
import io.pandora.mall.domian.chat.ChatConnectLog;

/**
 * @author Created by mr_zhou on 2021/1/6
 */
public interface ChatConnectLogService extends BaseService<ChatConnectLog> {

    void removeAll(String instanceFlag);
}
