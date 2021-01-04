package io.pandora.mall.push.online.convert;

import com.alibaba.fastjson.JSON;
import io.pandora.mall.push.message.MessageResponse;

import javax.websocket.EncodeException;
import javax.websocket.EndpointConfig;

/**
 * Created by John on 2019/11/12
 * 消息编码转换类
 */
public class MessageEncoder implements javax.websocket.Encoder.Text<MessageResponse> {

    @Override
    public String encode(MessageResponse message) throws EncodeException {
        return JSON.toJSONString(message);
    }

    @Override
    public void init(EndpointConfig endpointConfig) {
    }

    @Override
    public void destroy() {
    }
}
