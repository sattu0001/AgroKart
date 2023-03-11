package com.app.agri.dto;

import javax.persistence.Column;

public class AddressDto extends BaseDto{

	private Integer addressId;
	
	private Integer userId;

	
	private String fullAddress;

	
	private String country;

	
	private String state;

	
	private String district;

	
	private String city;

	
	private int userPincode;


	public Integer getAddressId() {
		return addressId;
	}


	public Integer getUserId() {
		return userId;
	}


	public void setUserId(Integer userId) {
		this.userId = userId;
	}


	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}


	public String getFullAddress() {
		return fullAddress;
	}


	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getDistrict() {
		return district;
	}


	public void setDistrict(String district) {
		this.district = district;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public int getUserPincode() {
		return userPincode;
	}


	public void setUserPincode(int userPincode) {
		this.userPincode = userPincode;
	}


	
	}


