//package io.pandora.mall.manage.mq.demo.direct;
//
//import org.springframework.amqp.rabbit.annotation.RabbitHandler;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//
///**
// * 消息生产者 demo
// *
// * @author Created by mr_zhou on 2020/11/25
// */
//@Component
//@RabbitListener(queues = "directQueue")
//public class DirectConsumer{
//
//    @RabbitHandler
//    public void receiveMsg(String msg){
//        System.out.println(msg);
//    }
//
//}
