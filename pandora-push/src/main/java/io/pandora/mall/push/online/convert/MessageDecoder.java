package io.pandora.mall.push.online.convert;

import com.alibaba.fastjson.JSON;
import io.pandora.mall.push.message.MessageResponse;

import javax.websocket.DecodeException;
import javax.websocket.EndpointConfig;

/**
 * Created by John on 2019/11/12
 * 消息对象转换类
 */
public class MessageDecoder implements javax.websocket.Decoder.Text<MessageResponse> {

    @Override
    public MessageResponse decode(String message) throws DecodeException {
        return JSON.parseObject(message, MessageResponse.class);
    }

    @Override
    public boolean willDecode(String s) {
        return true;
    }

    @Override
    public void init(EndpointConfig endpointConfig) {}

    @Override
    public void destroy() {}
}
