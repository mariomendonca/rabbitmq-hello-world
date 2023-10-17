package com.rmq.rmqexample.consumer;

import com.rmq.rmqexample.config.MessagingConfig;
import com.rmq.rmqexample.dto.OrderStatus;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class User {
    @RabbitListener(queues = MessagingConfig.QUEUE)
    public void consumeMessageFromQueue(OrderStatus orderStatus) {
        System.out.println("Message from queue" + orderStatus);
    }
}
