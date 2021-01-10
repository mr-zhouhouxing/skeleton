package io.pandora.mall.rabbit.impl;

import com.rabbitmq.client.Channel;
import io.pandora.mall.rabbit.IRabbitMqListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;

/**
 * 消息处理抽象类 所有消费者继承自该类 处理消息
 *
 * @author Created by mr_zhou on 2020/11/26
 */
public abstract class AbstractMessageListener implements ChannelAwareMessageListener , IRabbitMqListener {

    private Logger LOGGER = LoggerFactory.getLogger(AbstractMessageListener.class);

    private MessageConverter messageConverter = new Jackson2JsonMessageConverter();

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        long tag = message.getMessageProperties().getDeliveryTag();
        try {
            Object obj = messageConverter.fromMessage(message);
            boolean handleResult = handleMessage(obj);
            if (handleResult){
                channel.basicAck(tag,false);
            }else {
                LOGGER.error("消息处理【失败】message:{} ",message);
                channel.basicNack(tag,false,false);
            }
        }catch (Exception e){
            LOGGER.error("消息处理【异常】message:{},{} ,异常信息：{}",message,e.getMessage(),e);
            channel.basicNack(tag,false,false);
        }
    }
}
