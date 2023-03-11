package com.app.agri.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.app.agri.dto.UserDto;
import com.app.agri.dto.UserOrdersReport;
import com.app.agri.entity.User;
import com.lowagie.text.DocumentException;

import java.io.IOException;
public interface UserService {

	List<UserDto> findAllUsers();

	UserDto findUserById(int id);

	UserDto registerUser(@Valid UserDto u);

	UserDto findUserByEmailAndPassword(UserDto user);

	UserDto updateProfile(UserDto user, int userId);

	Boolean checkUserStatus(int id);

	List<UserDto> requestList();

	Boolean changeUserStatus(int userId, Boolean status);

	boolean deleteUser(int userId);
	
	String forgotPassword(String userEmail);

	String resetPassword(String userEmail, String otp, String password);

	UserDto findUserByEmail(String email);

	List<UserOrdersReport> generateReport(int userId);
	
	public void export(HttpServletResponse response,List<UserOrdersReport> userOrders) throws DocumentException, IOException;

	List<UserOrdersReport> getOrders();
}
