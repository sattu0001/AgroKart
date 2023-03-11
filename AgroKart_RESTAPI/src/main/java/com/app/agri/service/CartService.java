package com.app.agri.service;

import java.util.List;

import com.app.agri.dto.CartDto;

public interface CartService {
	List<CartDto> findAll();
	CartDto findByCartId(int id);
	CartDto addToCart( CartDto c);
	List<CartDto> findCartByUser(int userId);
	String addQuantity(int cartId);
	String removeQuantity(int cartId);
	Boolean deleteCartItem(int cartId);
	 int findTotalproducts(int userId) ;
	 int findTotalPrice(int userId) ;
}
