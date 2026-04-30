package com.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.models.Order;
import com.services.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
	
	
	private final OrderService service;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody Order order) {
    	if(order.product() == null)
    	{
    	  System.out.println("ORDER: " + order);
    	  return ResponseEntity.noContent().build();
    	}
        service.createOrder(order);
        return ResponseEntity.ok().build();
    }

}
