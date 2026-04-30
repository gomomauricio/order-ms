package com.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.models.Order;

@Component
public class OrderConsumer {
	
	 @KafkaListener(topics = "order-created", groupId = "group_id")
	    public void consume(Order order) {
	        System.out.println("Order received: " + order);
	    }
}
