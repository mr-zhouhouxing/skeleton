//package io.pandora.mall.manage.mq.demo.direct;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.amqp.rabbit.connection.CorrelationData;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.UUID;
//
///**
// * @author Created by mr_zhou on 2020/11/25
// */
//@Component
//public class DirectProducer {
//
//    @Autowired
//    private RabbitTemplate rabbitTemplate;
//
//    Logger logger = LoggerFactory.getLogger(DirectConsumer.class);
//
//    public void send(int i){
//        String date = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss").format(new Date());
//        String content = i + ": Hello " + date;
//
//        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
//        logger.info("class:{},message:{}","DirectConsumer",content);
//
//        rabbitTemplate.convertAndSend("amq.direct","direct_routingKey",content,correlationData);
//    }
//
//}
