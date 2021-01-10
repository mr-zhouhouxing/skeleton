package io.pandora.mall.push.offline.service;

import com.alibaba.fastjson.JSONObject;
import com.gexin.rp.sdk.base.IPushResult;
import io.pandora.mall.base.service.impl.BaseServiceImpl;
import io.pandora.mall.constant.Constant;
import io.pandora.mall.domian.system.SystemPushRecord;
import io.pandora.mall.exception.BadRequestException;
import io.pandora.mall.mapper.system.SystemPushClientMapper;
import io.pandora.mall.mapper.system.SystemPushRecordMapper;
import io.pandora.mall.push.message.MessageRequest;
import io.pandora.mall.push.enume.PushChannelType;
import io.pandora.mall.push.enume.PushType;
import io.pandora.mall.push.offline.dto.PushRequest;
import io.pandora.mall.push.offline.dto.PushResponse;
import io.pandora.mall.push.offline.getui.GeTuiServer;
import io.pandora.mall.redis.util.RedisUtils;
import io.pandora.mall.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

/**
 * @author Created by mr_zhou on 2021/1/4
 */
@Slf4j
@Service
public class SystemPushServiceImpl extends BaseServiceImpl<SystemPushRecordMapper, SystemPushRecord> implements SystemPushService {

    @Autowired
    private RedisUtils redisUtils;
    private final GeTuiServer geTuiServer;
    private final SystemPushClientMapper systemPushClientMapper;

    public SystemPushServiceImpl(GeTuiServer geTuiServer, SystemPushClientMapper systemPushClientMapper) {
        this.geTuiServer = geTuiServer;
        this.systemPushClientMapper = systemPushClientMapper;
    }

    @Override
    public void sendOffLineMessage(PushRequest request) {
        Integer channel = request.getChannel();
        if (!PushChannelType.verify(channel)) {
            throw new BadRequestException("Incorrect offline distribution channel");
        }

        MessageRequest message = request.getRequest();
        String clientId = getClientId(message.getToId());
        String channelName = PushChannelType.getName(channel);
        String title = PushType.getTitle(Integer.valueOf(message.getChatType()));
        PushResponse response = issuedMessage(channel, clientId, title, message.getData());

        log.info("[离线推送] {} 给 {} 推送消息,渠道:{},返回参数:{}",message.getFromId(),message.getToId(),channelName,response);

        this.save(SystemPushRecord.builder()
                .channel(channelName)
                .fromId(message.getFromId())
                .toId(message.getToId())
                .msgType(title)
                .parame(JSONObject.toJSONString(response))
                .taskId(response.getTaskId())
                .msgContent(message.getData())
                .createTime(new Date())
                .build());
    }

    @Override
    public void cleanClientId(Long userId) {
        // 清除缓存
        redisUtils.del(Constant.USER_PUSH_CLIENT + userId);
        systemPushClientMapper.updateClientId(userId, UUID.randomUUID().toString().replaceAll("-"," "));
    }

    private PushResponse issuedMessage(Integer channel, String toClientId , String title , String data){
        if (channel == PushChannelType.GeiTui.getChannel()){
            IPushResult result = geTuiServer.pushToSingle(toClientId, title, data);

            return new PushResponse(result.getResponse());

        }else if (channel == PushChannelType.JiGuang.getChannel()){
            // 极光通道下发
        }
        return new PushResponse(null);
    }

    private String getClientId(Long userId){
        if (StringUtils.isEmpty(userId)){
            throw new BadRequestException("获取第三方推送客户端ID失败,原因用户ID参数为空");
        }
        String clientId = redisUtils.get(Constant.USER_PUSH_CLIENT + userId).toString();
        if (StringUtils.isEmpty(clientId)){
            clientId = systemPushClientMapper.getClientIdByUserId(userId);
            // 有效期 一个月
            redisUtils.set(Constant.USER_PUSH_CLIENT + userId,clientId,Constant.EXPIRE_MONTH);
        }
        return clientId;
    }

}
