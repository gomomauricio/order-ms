package com.models;

 
public record Order( 
		Long id, 
		String product,  
		int quantity, 
		double price 
		) { }
