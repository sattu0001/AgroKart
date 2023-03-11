package com.app.agri.service;

import java.util.List;

import javax.validation.Valid;

import com.app.agri.dto.ProductDto;

public interface ProductService {

	List<ProductDto> findAllProducts();

	ProductDto findProductById(int id);

	ProductDto addProduct(@Valid ProductDto p);

	List<ProductDto> findProductsByCategory(int categoryId);

	Boolean deleteProduct(int productId);

	ProductDto updateProduct( ProductDto product, int id);

	List<ProductDto> findProductsByCategoryAndUser(int categoryId, int userId);
	
	 List<ProductDto> findProductsByUser(int userId);
	


}
