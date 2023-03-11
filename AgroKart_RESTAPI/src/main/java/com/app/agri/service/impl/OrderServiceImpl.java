package com.app.agri.service.impl;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.agri.dto.BaseDto;
import com.app.agri.dto.OrderDto;
import com.app.agri.dto.ProductDto;
import com.app.agri.entity.BaseEntity;
import com.app.agri.entity.Cart;
import com.app.agri.entity.Category;
import com.app.agri.entity.Order;
import com.app.agri.entity.OrderDetails;
import com.app.agri.entity.PaymentMode;
import com.app.agri.entity.Product;
import com.app.agri.entity.User;
import com.app.agri.exception.ContentNotFound;
import com.app.agri.repository.CartRepository;
import com.app.agri.repository.OrderRepository;
import com.app.agri.repository.PaymentModeRepository;
import com.app.agri.repository.ProductRepository;
import com.app.agri.repository.UserRepository;
import com.app.agri.service.OrderService;

@Service
public class OrderServiceImpl extends ConversionService implements OrderService {
	@Autowired
	private OrderRepository orderRepo;

	@Autowired
	private CartRepository cartRepo;
	
	@Autowired
	private PaymentModeRepository paymentModeRepo;
	
	@Autowired
	private ProductRepository productRepo;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public List<OrderDto> findAllOrders() {
		List<OrderDto> orderDto = null;

		try {
			List<Order> order = orderRepo.findAll();

			if (order != null) {
				orderDto = order.stream().map(o -> (OrderDto) super.convertEntityToDto(o, OrderDto.class))
						.collect(Collectors.toList());
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return orderDto;
	}

	@Override
	public OrderDto findOrderById(int id) {

		OrderDto orderDto = null;
		try {
			Optional<Order> optionalOrder = orderRepo.findById(id);

			if (!optionalOrder.isPresent()) {
				throw new ContentNotFound("Order does not exists");
			} else {
				Order order = optionalOrder.get();
				orderDto = (OrderDto) super.convertEntityToDto(order, OrderDto.class);
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return orderDto;
	}

	@Override
	public List<OrderDto> findOrdersByUser(int userId) {
		List<OrderDto> orderDto = null;

		try {
			List<Order> order = orderRepo.findByUser_UserIdAndCancelOrder(userId,Boolean.FALSE);

			if (order != null) {
				orderDto = order.stream().map(o -> (OrderDto) super.convertEntityToDto(o, OrderDto.class))
						.collect(Collectors.toList());
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return orderDto;
	}

	@Override
	public Boolean cancleOrderById(int id) {
		try {
			Optional<Order> optionalOrder = orderRepo.findById(id);

			if (!optionalOrder.isPresent()) {
				throw new ContentNotFound("Order does not exists");
			} else {
				Order order = optionalOrder.get();
				order.setCancelOrder(Boolean.TRUE);
				orderRepo.save(order);
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return Boolean.TRUE;
	}

	@Override
	@Transactional
	public OrderDto placeorder(OrderDto orderDto) {
		try {
			List<Integer> cartIds = orderDto.getCartIds();
			List<OrderDetails> orderDetailsList = new ArrayList<>();

			for (Integer cartId : cartIds) {
				Optional<Cart> optionalCart = cartRepo.findById(cartId);

				if (!optionalCart.isPresent()) {
					throw new ContentNotFound("Invalid Cart Id" + cartId);
				}
				Cart cart = optionalCart.get();
				OrderDetails orderDetails = new OrderDetails();
				orderDetails.setProduct(cart.getProduct());
				orderDetails.setQty(cart.getQty());
				Product product = productRepo.findById(orderDetails.getProduct().getProductId()).get();
				int qtyremoved = product.getProductQty() - orderDetails.getQty();
				product.setProductQty(qtyremoved);

				orderDetailsList.add(orderDetails);
	
			}
			Order order=(Order)convertDtoToEntity(orderDto, Order.class);
			order.setOrderDetailsList(orderDetailsList);
			LocalDate orderDate = LocalDate.now();
			LocalDate deliveryDate = orderDate.plusDays(3);
	
			order.setOrderDate(Date.valueOf(orderDate));
			order.setDeliveryDate(Date.valueOf(deliveryDate));
			orderRepo.save(order);
			cartRepo.deleteByUser_UserId(orderDto.getUserId());
			

		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}

		return orderDto;
	}
	public Order convertDtoToEntity(BaseDto src, Class<? extends BaseEntity> dest) {
		Order order = null;
		try {
			OrderDto orderDto = (OrderDto) src;

			order = (Order) super.convertDtoToEntity(src, dest);

			Optional<PaymentMode> optionalPaymentMode = paymentModeRepo.findById(orderDto.getPaymentModeId());

			Optional<User> optionalUser = userRepository.findById(orderDto.getUserId());

			if (!optionalPaymentMode.isPresent()) {
				throw new ContentNotFound("Invalid Payment Mode");
			}

			if (!optionalUser.isPresent()) {
				throw new ContentNotFound("Invalid User");
			}

			PaymentMode paymentMode = optionalPaymentMode.get();

			User user = optionalUser.get();

			order.setPayment(paymentMode);
			order.setUser(user);

		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return order;

	}
}
