package com.app.agri.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.agri.common.AgriContant;
import com.app.agri.dto.AddressDto;
import com.app.agri.dto.ProductDto;
import com.app.agri.dto.Response;
import com.app.agri.service.AddressService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = AgriContant.API_URI + "/address")
public class AddressController {
	
	@Autowired
	private AddressService addressService;

	@GetMapping("/getaddress")
	public ResponseEntity<?> findAddresssByUser(@RequestParam("userId") int userId) {
		return Response.success(addressService.findAddressByUser(userId));
	}
	@GetMapping("/getaddressById")
	public ResponseEntity<?> findAddresssByAddressId(@RequestParam("addressId") int addressId) {
		return Response.success(addressService.findAddressByAddressId(addressId));
	}
	
	@PostMapping("/saveaddress")
	public ResponseEntity<?> saveAddress(@Valid @RequestBody AddressDto a) {
		return Response.success(addressService.addAddress(a));
	}
	@DeleteMapping("/deleteaddress")
	public ResponseEntity<?> deleteAddress(@RequestParam("addressId") int addressId) {
		return Response.success(addressService.deleteAddress(addressId));
	}
}
