package io.pandora.mall.biz.rabbit.demo;

import io.pandora.mall.biz.rabbit.impl.AbstractMessageListener;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Created by mr_zhou on 2020/11/27
 */
public class OrderConsumer extends AbstractMessageListener {

    @Override
    public boolean handleMessage(Object obj) {
        System.out.println("OrderConsumer 消费者开始消费，消息内容：" + obj.toString());

        System.out.println("OrderConsumer 结束：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        return true;
    }


}
