package com.app.agri.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.agri.common.AgriContant;
import com.app.agri.dto.CartDto;
import com.app.agri.dto.Response;
import com.app.agri.service.CartService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = AgriContant.API_URI + "/cart")
public class CartController {
	@Autowired
	private CartService cartService;
	
	@GetMapping
	public ResponseEntity<?> findAll() {
		return Response.success(cartService.findAll());
	}
	@GetMapping("/cartbyid")
	public ResponseEntity<?> getCartById(@RequestParam("cartId") int id) {
		return Response.success(cartService.findByCartId(id));
	}
	
	@PostMapping("/addtocart")
	public ResponseEntity<?> saveUser(@Valid @RequestBody CartDto c) {
		return Response.success(cartService.addToCart(c));
	}

	@GetMapping("/user")
	public ResponseEntity<?> findCartByUser(@RequestParam("userId") int userId) {
		return Response.success(cartService.findCartByUser(userId));
	}
	
	@PutMapping("/add-qty")
	public ResponseEntity<?> addQuantity(@RequestParam("cartId") int cartId) {
		return Response.success(cartService.addQuantity(cartId));
	}
	
	@PutMapping("/remove-qty")
	public ResponseEntity<?> removeQuantity(@RequestParam("cartId") int cartId) {
		return Response.success(cartService.removeQuantity(cartId));
	}
	
	@DeleteMapping("/delete-cart-item")
	public ResponseEntity<?> deleteCartItem(@RequestParam("cartId") int cartId) {
		return Response.success(cartService.deleteCartItem(cartId));
	}
	
	@GetMapping("/totalproducts")
	public ResponseEntity<?> findTotalProdutsByUser(@RequestParam("userId") int userId) {
		return Response.success(cartService.findTotalproducts(userId));
	}
	
	@GetMapping("/totalprice")
	public ResponseEntity<?> findTotalPriceByUser(@RequestParam("userId") int userId) {
		return Response.success(cartService.findTotalPrice(userId));
	}
	
	
	//delete product from cart
	
	//get cartby userID
	
	
}
