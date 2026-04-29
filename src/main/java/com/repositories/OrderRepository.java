package com.repositories;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.models.Order;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OrderRepository  
{
	
	private final JdbcTemplate jdbcTemplate;
	
	public void save(Order order)
	{
		jdbcTemplate.update( "INSERT INTO orders( product, quantity, price) "
				+ " VALUES (?, ?, ?)", order.product(), order.quantity(), order.price() );
	}
	
	public List<Order> findAll()
	{
		return jdbcTemplate.query("SELECT * FROM orders", 
				 					(rs, rowNum) -> new Order(
				 			                rs.getLong("id"),
				 			                rs.getString("product"),
				 			                rs.getInt("quantity"),
				 			                rs.getDouble("price")
				 			            )
				 					);
	 }
}
