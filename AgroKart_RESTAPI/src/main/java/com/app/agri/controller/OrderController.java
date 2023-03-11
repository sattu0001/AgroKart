package com.app.agri.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.agri.common.AgriContant;
import com.app.agri.dto.OrderDto;
import com.app.agri.dto.Response;
import com.app.agri.service.OrderService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = AgriContant.API_URI + "/orders")
public class OrderController {
	@Autowired
	OrderService orderService;
	
	@GetMapping
	public ResponseEntity<?> findAll() {
		return Response.success(orderService.findAllOrders());
	}
	
	@GetMapping("/order")
	public ResponseEntity<?> getOrderById(@RequestParam("orderId") int id) {
		return Response.success(orderService.findOrderById(id));
	}
	
	@GetMapping("/user")
	public ResponseEntity<?> getOrderByUser(@RequestParam("userId") int userId) {
		return Response.success(orderService.findOrdersByUser(userId));
	}
	
	@GetMapping("/cancel-order")
	public ResponseEntity<?> cancelOrderById(@RequestParam("orderId") int id) {
		return Response.success(orderService.cancleOrderById(id));
	}

	@PostMapping("/placeorder")
	public ResponseEntity<?> placeOrder(@RequestBody OrderDto orderDto) {
		return Response.success(orderService.placeorder(orderDto));
	}
	
}
