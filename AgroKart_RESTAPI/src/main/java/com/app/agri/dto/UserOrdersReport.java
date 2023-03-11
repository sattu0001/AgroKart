package com.app.agri.dto;

import java.sql.Date;

public class UserOrdersReport {
	
	private String userName;
	
	private String cancelOrder="No";
	
	private int orderId;
	
	private Date orderDate;
	
	private Date deliveryDate;
	
	private double bill;
	
	private String paymentMode;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
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

	public double getBill() {
		return bill;
	}

	public void setBill(double bill) {
		this.bill = bill;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public String getCancelOrder() {
		return cancelOrder;
	}

	public void setCancelOrder(String cancelOrder) {
		this.cancelOrder = cancelOrder;
	}
	
}
