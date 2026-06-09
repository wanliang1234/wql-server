package com.wql.project.one.mq;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 这里面是当 Spring 容器启动，
 * Spring AMQP 会自动连接 RabbitMQ，然后检查该交换机是否存在。
 * 如果不存在，会在 RabbitMQ 服务端创建它。
 */
@Configuration
public class ExchangesBeanMq {

    @Bean
    public Exchange comWqlOneExchanges() {
        /**
         * new DirectExchange  创键 direct
         *  String name = 交换机的名字;
         *  boolean durable = 持久化;
         *  boolean autoDelete = 自动删除;
         *  Map<String, Object> arguments = 其他参数;
         */
        return new DirectExchange(ExchangesMq.COM_WQL_ONE_EXCHANGES, true, false, null);
    }

}
