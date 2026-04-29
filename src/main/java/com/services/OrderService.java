package com.services;

 
import org.springframework.stereotype.Service;

import com.kafka.KafkaProducer;
import com.models.Order;
import com.repositories.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService 
{
	private final OrderRepository repository;
	private final KafkaProducer producer;
	
	
	public void createOrder(Order order)
	{
		repository.save(order);
		producer.sendOrderEvent(order);
	}

}
