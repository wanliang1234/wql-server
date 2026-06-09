package com.wql.project.one.mq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueBeanMq {
    @Bean
    public static Queue comWqlOneQueues() {
        /**
         *  String name = 消息队列的名字;
         *  boolean durable = 持久化;
         */
        return new Queue(QueueMq.COM_WQL_ONE_QUEUES, true);
    }
}
