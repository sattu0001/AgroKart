package com.app.agri.service;

import java.util.List;

import com.app.agri.dto.OrderDetailsDto;
import com.app.agri.dto.ProductDto;
import com.app.agri.entity.OrderDetails;

public interface OrderDetailsService {
	
	List<OrderDetailsDto> findAllOrderDetails();

}
