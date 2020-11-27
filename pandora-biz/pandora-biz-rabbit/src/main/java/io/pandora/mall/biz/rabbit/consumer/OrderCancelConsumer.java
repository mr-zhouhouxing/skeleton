package io.pandora.mall.biz.rabbit.consumer;

import io.pandora.mall.biz.rabbit.impl.AbstractMessageListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Created by mr_zhou on 2020/11/27
 */
public class OrderCancelConsumer extends AbstractMessageListener {

    private Logger logger = LoggerFactory.getLogger(OrderCancelConsumer.class);

    /**
     * 订单支付超时取消
     *
     * @param obj
     * @return
     */
    @Override
    public boolean handleMessage(Object obj) {
        logger.info("订单进入付款超时处理.....");


        System.out.println("OrderConsumer 结束：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

        return true;
    }

}
