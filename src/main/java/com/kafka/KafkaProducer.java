package com.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.models.Order;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class KafkaProducer 
{
	
	private final KafkaTemplate<String, Object> kafkaTemplate;

	public void sendOrderEvent(Order order) {
		kafkaTemplate.send( "order-created", order );
	}
}
