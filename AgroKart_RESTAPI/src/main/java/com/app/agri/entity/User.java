package com.app.agri.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User extends BaseEntity {
	
	@Id//PK
	@GeneratedValue(strategy = GenerationType.IDENTITY)//autoincreament /indicates that the persistence provider must assign primary keys for the entity using a database identity column
	private Integer userId;

	@Column
	private String userName;

	@Column
	private String userPassword;

	@ManyToOne// many users having only single role
	@JoinColumn(name = "role_id")//fk
	private Role role;

	@Column
	private String userContactNo;

	@Column
	private String userEmail;

	@Column
	private String userAadharNo;

	@Column(columnDefinition = "boolean default false")//set default value as false
	private Boolean status;
	
	@Column
	private String token;
	//CascadeType.ALL is that the persistence will propagate (cascade) all EntityManager operations (PERSIST, REMOVE, REFRESH, MERGE, DETACH)
	//to the relating entities
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Address> addressList = new ArrayList<>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Product> productList = new ArrayList<>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Cart> cartList = new ArrayList<>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Order> orderist = new ArrayList<>();

	public User() {
		super();

	}

	public Integer getUserId() {
		return userId;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Cart> getCartList() {
		return cartList;
	}

	public void setCartList(List<Cart> cartList) {
		this.cartList = cartList;
	}

	public List<Order> getOrderist() {
		return orderist;
	}

	public void setOrderist(List<Order> orderist) {
		this.orderist = orderist;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserContactNo() {
		return userContactNo;
	}

	public void setUserContactNo(String userContactNo) {
		this.userContactNo = userContactNo;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserAadharNo() {
		return userAadharNo;
	}

	public void setUserAadharNo(String userAadharNo) {
		this.userAadharNo = userAadharNo;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public List<Address> getAddressList() {
		return addressList;
	}

	public List<Product> getProductList() {
		return productList;
	}

	public void setAddressList(List<Address> addressList) {
		for (Address ad : addressList)
			ad.setUser(this);
		this.addressList = addressList;
	}

	public void setProductList(List<Product> productList) {
		for (Product pd : productList)
			pd.setUser(this);
		this.productList = productList;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	

}