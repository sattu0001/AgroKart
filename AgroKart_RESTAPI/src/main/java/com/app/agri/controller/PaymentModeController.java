package com.app.agri.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.agri.common.AgriContant;
import com.app.agri.dto.Response;
import com.app.agri.service.PaymentModeService;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = AgriContant.API_URI+"/payment-modes")
public class PaymentModeController 
{
	@Autowired
private PaymentModeService paymentModeService;
	
	@GetMapping
	public ResponseEntity<?> findAll() {
		return Response.success(paymentModeService.findAllPaymentModes());
	}
}
