package com.app.agri.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
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
import com.app.agri.dto.Response;
import com.app.agri.dto.UserDto;
import com.app.agri.dto.UserOrdersReport;
import com.app.agri.service.UserService;
import com.lowagie.text.DocumentException;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = AgriContant.API_URI + "/users")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping
	public ResponseEntity<?> findAll() {
		return Response.success(userService.findAllUsers());
	}

	@GetMapping("/getuser")
	public ResponseEntity<?> getUserById(@RequestParam("userId") int id) {
		return Response.success(userService.findUserById(id));
	}
	
	@GetMapping("/getuserByEmail")
	public ResponseEntity<?> getUserByEmail(@RequestParam("email") String email) {
		return Response.success(userService.findUserByEmail(email));
	}

	@PostMapping("/sign-up")
	public ResponseEntity<?> saveUser(@Valid @RequestBody UserDto u) {
		return Response.success(userService.registerUser(u));
	}

	@PostMapping("/sign-in")
	public ResponseEntity<?> signIn(@RequestBody UserDto user) {

		return Response.success(userService.findUserByEmailAndPassword(user));

	}

	@PutMapping("/updateprofile")
	public ResponseEntity<?> updateProfile(@Valid @RequestBody UserDto user, @RequestParam("userId") int id) {
		return Response.success(userService.updateProfile(user, id));
	}

	@GetMapping("/user/status")
	public ResponseEntity<?> checkUserStatus(@RequestParam("userId") int id) {
		return Response.success(userService.checkUserStatus(id));
	}

	@GetMapping("/request-list")
	public ResponseEntity<?> requestList() {
		return Response.success(userService.requestList());
	}
	
	@PutMapping("/user/change-user-status")
	public ResponseEntity<?> changeUserStatus(@RequestParam("userId") int userId,@RequestParam("status") Boolean status) {
		return Response.success(userService.changeUserStatus(userId,status));
	}
	
	@DeleteMapping("/deleteuser")
	public ResponseEntity<?> deleteUser(@RequestParam("userId") int userId) {
		return Response.success(userService.deleteUser(userId));
	}
	
	@GetMapping("user/forgot-password")
	public ResponseEntity<?> forgotPassword(@RequestParam("userEmail") String userEmail) {

		return Response.success(userService.forgotPassword(userEmail));
	}
	
	@GetMapping("user/reset-password")
	public ResponseEntity<?> resetPassword(@RequestParam("userEmail") String userEmail,@RequestParam("otp") String otp,@RequestParam("password") String password) {

		return Response.success(userService.resetPassword(userEmail,otp,password));
		
	}
	@GetMapping("/buyer/generate-report")
	public void generateReport(@RequestParam("userId") int userId, HttpServletResponse response) {
		response.setContentType("application/pdf"); //setting our type response in pdf format
		
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition"; 
		String headerValue = "attachment; filename=user_orders" + currentDateTime + ".pdf"; //file name and its format while downloading
		response.setHeader(headerKey, headerValue);

		List<UserOrdersReport> userOrders = userService.generateReport(userId);
		try {
			userService.export(response, userOrders);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@GetMapping("/generate-orders-report")
	public void generateOrdersReport(HttpServletResponse response) {
		response.setContentType("application/pdf");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=orders-" + currentDateTime + ".pdf";
		response.setHeader(headerKey, headerValue);

		List<UserOrdersReport> orders = userService.getOrders();
		try {
			userService.export(response, orders);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
