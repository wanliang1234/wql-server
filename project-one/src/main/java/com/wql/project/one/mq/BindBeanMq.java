package com.wql.project.one.mq;

import org.springframework.amqp.core.Binding;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BindBeanMq {
    @Bean
    public Binding oneOneBind() {
        return new Binding(QueueMq.COM_WQL_ONE_QUEUES,
                Binding.DestinationType.QUEUE,
                ExchangesMq.COM_WQL_ONE_EXCHANGES,
                QueueKeyMq.COM_WQL_ONE_QUEUES_KEY,
                null);
    }
}
