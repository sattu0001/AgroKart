package com.app.agri.service;

import java.util.List;

import javax.validation.Valid;

import com.app.agri.dto.AddressDto;

public interface AddressService {

	List<AddressDto> findAddressByUser(int userId);

	AddressDto addAddress(@Valid AddressDto a);

	boolean deleteAddress(int addressId);
	
	AddressDto findAddressByAddressId(int addressId);

}
