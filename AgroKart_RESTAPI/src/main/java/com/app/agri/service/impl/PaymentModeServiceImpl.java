package com.app.agri.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.agri.dto.PaymentModeDto;
import com.app.agri.dto.ProductDto;
import com.app.agri.entity.PaymentMode;
import com.app.agri.entity.Product;
import com.app.agri.repository.PaymentModeRepository;
import com.app.agri.service.PaymentModeService;

@Service
public class PaymentModeServiceImpl extends ConversionService implements PaymentModeService {
	@Autowired
	private PaymentModeRepository paymentModeRepo;

	@Override
	public List<PaymentModeDto> findAllPaymentModes() {
		List<PaymentModeDto> paymentModeDto = null;

		try {
			List<PaymentMode> paymentMode = paymentModeRepo.findAll();

			if (paymentMode != null) {
				paymentModeDto = paymentMode.stream().map(pay -> (PaymentModeDto) super.convertEntityToDto(pay, PaymentModeDto.class))
						.collect(Collectors.toList());
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return paymentModeDto;
	}
}
