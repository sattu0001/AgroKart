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
import com.app.agri.dto.ProductDto;
import com.app.agri.dto.Response;
import com.app.agri.dto.UserDto;
import com.app.agri.service.ProductService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = AgriContant.API_URI+"/products")
public class ProductController 
{
	@Autowired
	private ProductService productService;
	
	@GetMapping
	public ResponseEntity<?> findAll() {
		return Response.success(productService.findAllProducts());
	}

	@GetMapping("/productbyid")
	public ResponseEntity<?> getProductById(@RequestParam("productId") int id) {
		return Response.success(productService.findProductById(id));
	}

	@PostMapping("/addproduct")
	public ResponseEntity<?> saveProduct(@Valid @RequestBody ProductDto p) {
		return Response.success(productService.addProduct(p));
	}
	
	@GetMapping("/category")
	public ResponseEntity<?> findProductsByCategory(@RequestParam("categoryId") int categoryId) {
		return Response.success(productService.findProductsByCategory(categoryId));
	}
	
	@GetMapping("/user")
	public ResponseEntity<?> findProductsByUser(@RequestParam("userId") int userId) {
		return Response.success(productService.findProductsByUser(userId));
	}
	
	@GetMapping("user/category")
	public ResponseEntity<?> findProductsByCategoryAndUser(@RequestParam("categoryId") int categoryId,@RequestParam("userId") int userId) {
		return Response.success(productService.findProductsByCategoryAndUser(categoryId,userId));
	}
	
	
	@DeleteMapping("/delete-product")
	public ResponseEntity<?> deleteProduct(@RequestParam("productId") int productId) {
		return Response.success(productService.deleteProduct(productId));
	}
	@PutMapping("/updateproduct")
	public ResponseEntity<?> updateProduct(@Valid @RequestBody ProductDto product, @RequestParam("productId") int id) {
		return Response.success(productService.updateProduct(product, id));
	}
	
//latest 
}
