package com.app.agri.service;

import java.util.List;

import com.app.agri.dto.OrderDto;
import com.app.agri.dto.ProductDto;

public interface OrderService {
	
	
	public List<OrderDto> findAllOrders(); 
	public OrderDto findOrderById(int id);
	public List<OrderDto> findOrdersByUser(int userId);
	public Boolean cancleOrderById(int id);
	public OrderDto placeorder(OrderDto orderDto);

}
