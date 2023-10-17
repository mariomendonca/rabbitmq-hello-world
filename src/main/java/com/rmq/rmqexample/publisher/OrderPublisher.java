package com.rmq.rmqexample.publisher;

import com.rmq.rmqexample.dto.OrderStatus;
import com.rmq.rmqexample.config.MessagingConfig;
import com.rmq.rmqexample.dto.Order;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/order")
public class OrderPublisher {
    @Autowired
    private RabbitTemplate template;

    @PostMapping("/{restaurantName}")
    public String bookOrder(@RequestBody Order order, @PathVariable String restaurantName) {
        order.setOrderId(UUID.randomUUID().toString());
//        restaurant service
//        payment service
        OrderStatus orderStatus = new OrderStatus(
                order,
                "PROCESS",
                "order placed successfully in " + restaurantName
        );

        template.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTING_KEY, orderStatus);
        return "success";
    }
}
