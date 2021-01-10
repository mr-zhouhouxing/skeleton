package io.pandora.mall.chat.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.pandora.mall.base.service.impl.BaseServiceImpl;
import io.pandora.mall.chat.service.ChatConnectLogService;
import io.pandora.mall.domian.chat.ChatConnectLog;
import io.pandora.mall.mapper.chat.ChatConnectLogMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Created by mr_zhou on 2021/1/6
 */
@Slf4j
@Service
public class ChatConnectLogServiceImpl extends BaseServiceImpl<ChatConnectLogMapper, ChatConnectLog> implements ChatConnectLogService {


    @Override
    public void removeAll(String instanceFlag) {
        this.remove(new QueryWrapper<ChatConnectLog>().eq("instance_flag",instanceFlag));
    }
}
