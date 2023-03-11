package com.app.agri.dto;

import javax.validation.constraints.NotEmpty;

import com.app.agri.entity.Role;

public class UserDto extends BaseDto {
	
	private Integer userId;

	//validations
	@NotEmpty(message = "Email cannot be empty")
	private String userEmail;
	
	@NotEmpty(message = "Password cannot be empty")
	private String userPassword;
	
	@NotEmpty(message = "userName cannot be empty")
	private String userName;
	
	private int userRole;
	
	private Role role;
	

	@NotEmpty(message = "userContactNo cannot be empty")
	private String userContactNo;

	@NotEmpty(message = "userAadharNo cannot be empty")
	private String userAadharNo;

	private Boolean status=false;

	public UserDto() {
		super();
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getUserRole() {
		return userRole;
	}

	public void setUserRole(int userRole) {
		this.userRole = userRole;
	}

	public String getUserContactNo() {
		return userContactNo;
	}

	public void setUserContactNo(String userContactNo) {
		this.userContactNo = userContactNo;
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
}
