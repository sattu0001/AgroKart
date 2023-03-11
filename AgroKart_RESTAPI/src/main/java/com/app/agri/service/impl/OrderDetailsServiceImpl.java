package com.app.agri.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.agri.dto.OrderDetailsDto;
import com.app.agri.dto.ProductDto;
import com.app.agri.entity.OrderDetails;
import com.app.agri.entity.Product;
import com.app.agri.repository.OrderDetailsRepository;
import com.app.agri.service.OrderDetailsService;

@Service
public class OrderDetailsServiceImpl extends ConversionService implements OrderDetailsService {
	@Autowired
	private OrderDetailsRepository orderDetailsRepo;
	
	@Override
	public List<OrderDetailsDto> findAllOrderDetails() {
		List<OrderDetailsDto> orderDetailsDto = null;
		
		try {
			List<OrderDetails> orderdetails = orderDetailsRepo.findAll();

			if (orderdetails != null) {
				orderDetailsDto = orderdetails.stream().map(od -> (OrderDetailsDto) super.convertEntityToDto(od, OrderDetailsDto.class))
						.collect(Collectors.toList());
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return orderDetailsDto;
	}
	
	
	
}
