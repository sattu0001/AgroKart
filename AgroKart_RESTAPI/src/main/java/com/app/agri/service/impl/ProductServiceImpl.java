package com.app.agri.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.agri.dto.BaseDto;
import com.app.agri.dto.ProductDto;
import com.app.agri.entity.BaseEntity;
import com.app.agri.entity.Category;
import com.app.agri.entity.Product;
import com.app.agri.entity.User;
import com.app.agri.exception.AlreadyExistsException;
import com.app.agri.exception.ContentNotFound;
import com.app.agri.exception.InvalidRequestException;
import com.app.agri.repository.CategoryRepository;
import com.app.agri.repository.ProductRepository;
import com.app.agri.repository.UserRepository;
import com.app.agri.service.ProductService;

@Service
public class ProductServiceImpl extends ConversionService implements ProductService {
	@Autowired
	private ProductRepository productRepo;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<ProductDto> findAllProducts() {
		List<ProductDto> productsDto = null;

		try {
			List<Product> products = productRepo.findByIsActive(Boolean.TRUE);

			if (products != null) {
				productsDto = products.stream().map(p -> (ProductDto) super.convertEntityToDto(p, ProductDto.class))
						.collect(Collectors.toList());
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return productsDto;
	}

	@Override
	public ProductDto findProductById(int id) {

		ProductDto productDto = null;
		try {
			Optional<Product> optionalProduct = productRepo.findById(id);

			if (!optionalProduct.isPresent()) {
				throw new ContentNotFound("Product does not exists");
			} else {
				Product product = optionalProduct.get();
				if(product.getIsActive()==Boolean.FALSE) {
					throw new InvalidRequestException("Product is deleted/Inactive");
				}
				productDto = (ProductDto) super.convertEntityToDto(product, ProductDto.class);

				// set Category Id and userId to Dto object example
				productDto.setCategoryId(product.getCategory().getCategoryId());
				productDto.setUserId(product.getUser().getUserId());

			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return productDto;
	}

	@Override
	public ProductDto addProduct(ProductDto p) {
		try {
			double finalPrice = (p.getProductPrice() - (p.getProductPrice() * p.getDiscount()) / 100);
			p.setFinalPrice(finalPrice);
			Product product = productRepo.findByProductName(p.getProductName());

			if (product != null) {
				throw new AlreadyExistsException("Product is already Exists");
			}
			product = (Product) convertDtoToEntity(p, Product.class);

			productRepo.save(product);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return p;
	}

	public Product convertDtoToEntity(BaseDto src, Class<? extends BaseEntity> dest) {
		Product product = null;
		try {
			ProductDto productDto = (ProductDto) src;

			product = (Product) super.convertDtoToEntity(src, dest);

			Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());

			Optional<User> optionalUser = userRepository.findById(productDto.getUserId());

			if (!optionalCategory.isPresent()) {
				throw new ContentNotFound("Invalid Category");
			}

			if (!optionalUser.isPresent()) {
				throw new ContentNotFound("Invalid User");
			}

			Category category = optionalCategory.get();

			User user = optionalUser.get();

			product.setCategory(category);
			product.setUser(user);

		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return product;

	}

	@Override
	public List<ProductDto> findProductsByCategory(int categoryId) {
		List<ProductDto> productsDto = null;

		try {
			List<Product> products = productRepo.findByCategory_CategoryIdAndIsActive(categoryId,Boolean.TRUE);

			if (products != null) {
				productsDto = products.stream().map(p -> (ProductDto) super.convertEntityToDto(p, ProductDto.class))
						.collect(Collectors.toList());
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return productsDto;
	}

	@Override
	public List<ProductDto> findProductsByUser(int userId) {
		List<ProductDto> productsDto = null;

		try {
			List<Product> products = productRepo.findByUser_UserIdAndIsActive(userId,Boolean.TRUE);

			if (products != null) {
				productsDto = products.stream().map(p -> (ProductDto) super.convertEntityToDto(p, ProductDto.class))
						.collect(Collectors.toList());
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return productsDto;
	}

	@Override
	public List<ProductDto> findProductsByCategoryAndUser(int categoryId, int userId) {
		List<ProductDto> productsDto = null;

		try {
			List<Product> products = productRepo.findByCategory_CategoryIdAndUser_UserIdAndIsActive(categoryId, userId,Boolean.TRUE);

			if (products != null) {
				productsDto = products.stream().map(p -> (ProductDto) super.convertEntityToDto(p, ProductDto.class))
						.collect(Collectors.toList());
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return productsDto;
	}

	@Override
	public Boolean deleteProduct(int productId) {
		try {
			Optional<Product> optionalProduct = productRepo.findById(productId);

			if (!optionalProduct.isPresent()) {
				throw new ContentNotFound("Product does not exists");
			} else {
				Product product=optionalProduct.get();
				product.setIsActive(Boolean.FALSE);
				productRepo.save(product);

			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return Boolean.FALSE;
	}

	@Override
	public ProductDto updateProduct(ProductDto productDto, int productid) {
		try {
			Optional<Product> up = productRepo.findById(productid);

			if (!up.isPresent()) {
				throw new ContentNotFound("Product does not exist");
			}
			Product product = up.get();
			product = (Product) convertDtoToEntity(productDto, Product.class);
			product.setProductId(productid);

			productRepo.save(product);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return productDto;
	}
}
