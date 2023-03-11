package com.app.agri.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.agri.dto.BaseDto;
import com.app.agri.dto.CartDto;
import com.app.agri.dto.ProductDto;
import com.app.agri.entity.BaseEntity;
import com.app.agri.entity.Cart;
import com.app.agri.entity.Category;
import com.app.agri.entity.Product;
import com.app.agri.entity.User;
import com.app.agri.exception.AlreadyExistsException;
import com.app.agri.exception.ContentNotFound;
import com.app.agri.exception.InvalidRequestException;
import com.app.agri.repository.CartRepository;
import com.app.agri.repository.ProductRepository;
import com.app.agri.repository.UserRepository;
import com.app.agri.service.CartService;

@Service
public class CartServiceImpl extends ConversionService implements CartService {

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private UserRepository userrepo;

	@Autowired
	private ProductRepository productrepo;

	@Override
	public List<CartDto> findAll() {
		List<CartDto> cartDto = null;

		try {
			List<Cart> cart = cartRepository.findAll();

			if (cart != null) {
				cartDto = cart.stream().map(c -> (CartDto) super.convertEntityToDto(c, CartDto.class))
						.collect(Collectors.toList());
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return cartDto;
	}

	@Override
	public CartDto findByCartId(int id) {

		CartDto cartDto = null;
		try {
			Optional<Cart> optionalCart = cartRepository.findById(id);

			if (!optionalCart.isPresent()) {
				throw new ContentNotFound("Cart does not exists");
			} else {
				Cart cart = optionalCart.get();
				cartDto = (CartDto) super.convertEntityToDto(cart, CartDto.class);
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return cartDto;
	}

	public CartDto addToCart(CartDto c) {
		try {
			// Cart cart = cartRepository.findById(c.getUserId()).get();

			/*
			 * if (cart != null) { throw new
			 * AlreadyExistsException("Cart is already Exists"); }
			 */
			Cart cart = (Cart) convertDtoToEntity(c, Cart.class);
			if(cart.getQty()==0)
				cart.setQty(1);
		cartRepository.save(cart);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return c;
	}

	public Cart convertDtoToEntity(BaseDto src, Class<? extends BaseEntity> dest) {
		Cart cart = null;
		try {
			CartDto cartDto = (CartDto) src;

			cart = (Cart) super.convertDtoToEntity(src, dest);

			Optional<Product> optionalProduct = productrepo.findById(cartDto.getProductId());

			Optional<User> optionalUser = userrepo.findById(cartDto.getUserId());

			if (!optionalProduct.isPresent()) {
				throw new ContentNotFound("Invalid Product");
			}

			if (!optionalUser.isPresent()) {
				throw new ContentNotFound("Invalid User");
			}

			Product product = optionalProduct.get();

			User user = optionalUser.get();

			cart.setProduct(product);
			cart.setUser(user);

		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return cart;

	}

	@Override
	public List<CartDto> findCartByUser(int userId) {
		List<CartDto> cartDto = null;

		try {
			List<Cart> cart = cartRepository.findByUser_UserId(userId);

			if (cart != null) {
				cartDto = cart.stream().map(p -> (CartDto) super.convertEntityToDto(p, CartDto.class))
						.collect(Collectors.toList());
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return cartDto;
	}

	@Override
	public String addQuantity(int cartId) {
		try {
			Optional<Cart> optionalCart = cartRepository.findById(cartId);

			if (!optionalCart.isPresent()) {
				throw new ContentNotFound("Invalid Cart Id");
			} else {
				Cart cart = optionalCart.get();
				cart.setQty(cart.getQty()+1);
				cartRepository.save(cart);
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return "Successfully Added";
	}

	@Override
	public String removeQuantity(int cartId) {
		try {
			Optional<Cart> optionalCart = cartRepository.findById(cartId);

			if (!optionalCart.isPresent()) {
				throw new ContentNotFound("Invalid Cart Id");
			} else {
				Cart cart = optionalCart.get();
				if(cart.getQty()<=1) {
					throw new InvalidRequestException("Quantity should not be less than 1");
				}
				cart.setQty(cart.getQty()-1);
				cartRepository.save(cart);
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return "Successfully removed";
	}

	@Override
	public Boolean deleteCartItem(int cartId) {
		try {
			Optional<Cart> optionalCart = cartRepository.findById(cartId);

			if (!optionalCart.isPresent()) {
				throw new ContentNotFound("Invalid Cart Id");
			} else {
				cartRepository.deleteById(cartId);

			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return Boolean.TRUE;
	}
	@Override
	public int findTotalproducts(int userId) {
		List<CartDto> cartDto = null;
		int totalProducts = 0;
		try {
			List<Cart> cart = cartRepository.findByUser_UserId(userId);

			if (cart != null) {
				for(Cart item:cart)
				{
					totalProducts +=item.getQty();
				}
				
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return totalProducts;
	}
	
	@Override
	public int findTotalPrice(int userId) {
		List<CartDto> cartDto = null;
		int totalPrice = 0;
		try {
			List<Cart> cart = cartRepository.findByUser_UserId(userId);

			if (cart != null) {
				for(Cart item:cart)
				{
					totalPrice +=item.getProduct().getFinalPrice()*item.getQty();
				}
				
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return totalPrice;
	}

}