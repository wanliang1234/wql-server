package com.wql.project.one.mq.consumer;

import com.wql.project.one.mq.QueueMq;
import com.wql.project.one.query.Mb01CrmSignatureQuery;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class OneConsumption {
    /**
     * 消费在one中的交换机
     * msg 消息
     */
    @RabbitListener(queues = QueueMq.COM_WQL_ONE_QUEUES)
    public void consumption(String msg) {
        System.out.println("----------" + msg);
    }
}
