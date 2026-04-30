package com.models;

 

public record Order( 
		Long id, 
		String product,  
		Integer quantity, 
		Double price 
		) { }



 