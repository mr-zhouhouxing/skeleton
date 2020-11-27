package io.pandora.mall.biz.rabbit;

import java.util.Map;

/**
 * <small>
 *     Queue
 *     <p> 参数详解：https://blog.csdn.net/fsgsggd/article/details/81349553 </p>
 * </small>
 * @author Created by mr_zhou on 2020/11/26
 */
public interface IRabbitMqQueue {

    // 队列名称
    String queueName();

    // 是否持久化
    default boolean durable(){ return true;}

    /**
     *     TODO: 2020/11/27  是否排外的，有两个作用，
     * 一：首先是强调首次声明，因为另外一个连接无法声明一个同样的排他性队列；其次是只区别连接（Connection）
     *     而不是通道（Channel），从同一个连接创建的不同的通道可以同时访问某一个排他性的队列。
     *     这里说的连接是指一个AMQPConnection，以RabbitMQ的Java客户端为例：Connection conn = factory.newConnection();
     *     如果试图在一个不同的连接中重新声明或访问（如publish，consume）该排他性队列，
     *     会得到资源被锁定的错误：ESOURCE_LOCKED - cannot obtain exclusive access to locked queue 'UserLogin2'。
     *     还有一点是十分重要的：如果一个连接声明一个队列a，其他连接就不能声明队列a，
     *     也就意味着不能从队列a中获取消息进行消费，但是可以向队列a中发送信息。
     *
     * 二：该队列是否是私有的private，如果不是排外的，可以使用两个消费者都访问同一个队列，没有任何问题。
     *     如果是排外的，会对当前队列加锁，其他通道channel是不能访问的，
     *
     * 如果强制访问会报异常：
     * com.rabbitmq.client.ShutdownSignalException: channel error; protocol method:
     * #method<channel.close>(reply-code=405, reply-text=RESOURCE_LOCKED - cannot obtain exclusive access to
     * locked queue 'queue_name' in vhost '/', class-id=50, method-id=20)
     *
     * 一般等于true的话用于一个队列只能有一个消费者来消费的场景
     * @return
     */
    default boolean exclusive(){return false;}

    // 是否自动删除
    default boolean autoDelete(){return false;}

    // 其他属性设置
    default Map<String,Object> arguments(){return null;}

    // 默认延时队列名称
    default String delayQueueName(){ return "delay." + queueName(); }

}
