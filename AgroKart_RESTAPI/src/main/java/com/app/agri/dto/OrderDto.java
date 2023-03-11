package com.app.agri.dto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.app.agri.entity.OrderDetails;
import com.app.agri.entity.PaymentMode;
import com.app.agri.entity.User;

public class OrderDto extends BaseDto{
	
	private Integer orderId;
	
	private Date orderDate;

	private Date deliveryDate;

	private Double totalCost;

	private Boolean cancelOrder=false;
	
	private List<OrderDetailsDto> orderDetailsList;
	
	private int  paymentModeId;

	public Double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(Double totalCost) {
		this.totalCost = totalCost;
	}

	private int  userId;
	
	
	
	private List<Integer> cartIds;

	public List<Integer> getCartIds() {
		return cartIds;
	}

	public void setCartIds(List<Integer> cartIds) {
		this.cartIds = cartIds;
	}

	public List<OrderDetailsDto> getOrderDetailsList() {
		return orderDetailsList;
	}

	public void setOrderDetailsList(List<OrderDetailsDto> orderDetailsList) {
		this.orderDetailsList = orderDetailsList;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public Double getTotalAmount() {
		return totalCost;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalCost = totalAmount;
	}

	public Boolean getCancelOrder() {
		return cancelOrder;
	}

	public void setCancelOrder(Boolean cancelOrder) {
		this.cancelOrder = cancelOrder;
	}

	public int getPaymentModeId() {
		return paymentModeId;
	}

	public void setPaymentModeId(int paymentModeId) {
		this.paymentModeId = paymentModeId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	

}
