package com.app.agri.service.impl;

import java.awt.Color;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.agri.config.EmailConfig;
import com.app.agri.dto.BaseDto;
import com.app.agri.dto.UserDto;
import com.app.agri.dto.UserOrdersReport;
import com.app.agri.entity.BaseEntity;
import com.app.agri.entity.Order;
import com.app.agri.entity.Role;
import com.app.agri.entity.User;
import com.app.agri.exception.AlreadyExistsException;
import com.app.agri.exception.ContentNotFound;
import com.app.agri.repository.OrderRepository;
import com.app.agri.repository.RoleRepository;
import com.app.agri.repository.UserRepository;
import com.app.agri.service.UserService;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Service
public class UserServiceImpl extends ConversionService implements UserService {

	@Autowired
	private EmailConfig emailConfig;
	
	@Autowired
	private UserRepository userRepo;

	@Autowired
	private RoleRepository roleRepository;
	

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private OrderRepository orderRepo;

	public List<UserDto> findAllUsers() {
		List<UserDto> usersDto = null;
		try {
			List<User> users = userRepo.findAll();

			if (users != null) {
				usersDto = users.stream().map(u -> (UserDto) super.convertEntityToDto(u, UserDto.class))
						.collect(Collectors.toList());
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return usersDto;
	}

	public UserDto findUserById(int id) {
		UserDto userDto = null;
		try {
			Optional<User> optionalUser = userRepo.findById(id);

			if (!optionalUser.isPresent()) {
				throw new ContentNotFound("User does not exists");
			} else {
				User user = optionalUser.get();
				userDto = (UserDto) super.convertEntityToDto(user, UserDto.class);
				userDto.setUserRole(user.getRole().getRoleId());
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return userDto;
	}

	public UserDto registerUser(UserDto u) {
		try {
			User us = userRepo.findByUserEmail(u.getUserEmail());

			if (us != null) {
				throw new AlreadyExistsException("User is already Exists");
			}
			User user = (User) convertDtoToEntity(u, User.class);
			user.setUserPassword(passwordEncoder.encode(u.getUserPassword()));
			user = userRepo.save(user);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return u;
	}

	// check mail and pass
	public UserDto findUserByEmailAndPassword(UserDto user) {
		UserDto userDto = null;
		try {
			if (user.getUserEmail() == null) {
				throw new ContentNotFound("Email cannot be empty");
			}
			User dbUser = userRepo.findByUserEmail(user.getUserEmail());
			
			//matches decodes db user password and compare 
			if (dbUser == null || !passwordEncoder.matches(user.getUserPassword(), dbUser.getUserPassword())) {
				throw new ContentNotFound("Invalid email or Password");
			}
			userDto = (UserDto) super.convertEntityToDto(dbUser, UserDto.class);

		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}

		return userDto;
	}

	public BaseEntity convertDtoToEntity(BaseDto src, Class<? extends BaseEntity> dest) {
		User user = null;
		try {
			UserDto userDto = (UserDto) src;

			user = (User) super.convertDtoToEntity(src, dest);

			Optional<Role> optionalRole = roleRepository.findById(userDto.getUserRole());

			if (!optionalRole.isPresent()) {
				throw new ContentNotFound("Invalid Role");
			}
			Role role = optionalRole.get();
			user.setRole(role);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return user;
	}

	@Override
	public UserDto updateProfile(UserDto userDto, int userid) {
		try {
			Optional<User> us = userRepo.findById(userid);

			if (!us.isPresent()) {
				throw new ContentNotFound("User does not exist");
			}
			User user = us.get();
			user = (User) convertDtoToEntity(userDto, User.class);
			user.setUserId(userid);

			user.setUserPassword(passwordEncoder.encode(userDto.getUserPassword()));
			userRepo.save(user);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return userDto;
	}

	@Override
	public Boolean checkUserStatus(int userid) {
		User user = null;
		try {
			Optional<User> us = userRepo.findById(userid);

			if (!us.isPresent()) {
				throw new ContentNotFound("User does not exist");
			}
			user = us.get();

		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return user.getStatus();
	}

	@Override
	public List<UserDto> requestList() {

		List<UserDto> usersDto = null;
		try {
			List<User> users = userRepo.findUnApprovedRequests(Boolean.FALSE);

			if (users != null) {
				usersDto = users.stream().map(u -> (UserDto) super.convertEntityToDto(u, UserDto.class))
						.collect(Collectors.toList());
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return usersDto;
	}

	@Override
	public Boolean changeUserStatus(int userId, Boolean status) {
		User user = null;
		try {
			Optional<User> us = userRepo.findById(userId);

			if (!us.isPresent()) {
				throw new ContentNotFound("User does not exist");
			}
			user = us.get();
			user.setStatus(status);
			userRepo.save(user);

		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return user.getStatus();
	}

	@Override
	public boolean deleteUser(int userId) {
		try {
			Optional<User> optionalUser = userRepo.findById(userId);

			if (!optionalUser.isPresent()) {
				throw new ContentNotFound("User does not exists");
			} else {
				userRepo.deleteById(userId);

			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return Boolean.TRUE;
	}

	@Override
	public String forgotPassword(String userEmail) {
		try {
			User user = userRepo.findByUserEmail(userEmail);

			if (user==null) {
				throw new ContentNotFound("User does not exists");
			} else {

				Random rnd = new Random();
				int number = rnd.nextInt(999999);

				// this will convert any number sequence into 6 character.
				String token=String.format("%06d", number);
				user.setToken(token);
				userRepo.save(user);
				emailConfig.sendEmail(user.getUserEmail(),token,user.getUserName());
				
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return "Email sent Successfully";
	}

	@Override
	public String resetPassword(String userEmail, String otp,String password) {
		try {
			User user = userRepo.findByUserEmail(userEmail);

			if (user==null) {
				throw new ContentNotFound("User does not exists");
			} else {
				
				if(!user.getToken().equals(otp)) {
					throw new ContentNotFound("Invalid OTP");
				}
				user.setUserPassword(passwordEncoder.encode(password));
				
				userRepo.save(user);	
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return "Password reset Successfully";
	}

	@Override
	public UserDto findUserByEmail(String email) {
		UserDto userDto = null;
		try {
			User user = userRepo.findByUserEmail(email);

			if (user==null) {
				throw new ContentNotFound("User does not exists");
			} else {
				userDto = (UserDto) super.convertEntityToDto(user, UserDto.class);
				userDto.setUserRole(user.getRole().getRoleId());
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return userDto;
	}
	
	//To generate report using user Id
	@Override
	public List<UserOrdersReport> generateReport(int userId) {
		List<Order> orders = orderRepo.findByUser_UserId(userId);
		List<UserOrdersReport> report = orders.stream().map(o -> convertOrderToUserOrdersReport(o))
				.collect(Collectors.toList());
		return report;
	}
	
	//creating table  and  formatting Table header
	private void writeTableHeader(PdfPTable table) {
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.GREEN);
		cell.setPadding(7);

		Font font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(Color.WHITE);

		cell.setPhrase(new Phrase("User Name", font));

		table.addCell(cell);

		cell.setPhrase(new Phrase("Order Id", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Order Date", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Delivery Date", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Bill", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Payment Mode", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Order Cancelled", font));
		table.addCell(cell);
	}

	//adding data to table  stored in List<UserOrdersReport>
	private void writeTableData(PdfPTable table,List<UserOrdersReport> listUsers) {
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		for (UserOrdersReport user : listUsers) {
			table.addCell(user.getUserName());
			table.addCell(String.valueOf(user.getOrderId()));
			table.addCell(dateFormatter.format(user.getOrderDate()));
			table.addCell(dateFormatter.format(user.getDeliveryDate()));
			table.addCell(String.valueOf(user.getBill()));
			table.addCell(user.getPaymentMode());
			table.addCell(user.getCancelOrder());
		}
	}

	public void export(HttpServletResponse response,List<UserOrdersReport> userOrders) throws DocumentException, IOException {
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());

		document.open();
		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(18);
		font.setColor(Color.BLACK);

		Paragraph p = new Paragraph("List of Orders", font);
		p.setAlignment(Paragraph.ALIGN_CENTER);

		document.add(p);

		PdfPTable table = new PdfPTable(7); //7 is column no in PDF table
		table.setWidthPercentage(100f);
		table.setWidths(new float[] { 3.5f, 3.5f, 4.5f, 4.5f, 3.5f, 3.5f, 3.5f }); //width of each column
		table.setSpacingBefore(10);

		writeTableHeader(table); // writing header to table
		writeTableData(table,userOrders); // writing data to table
		document.add(table); //adding table to document

		document.close();

	}

	private UserOrdersReport convertOrderToUserOrdersReport(Order o) {
		UserOrdersReport userOrdersReport = new UserOrdersReport();
		if (o.getCancelOrder()!=null && o.getCancelOrder()) {
			userOrdersReport.setCancelOrder("Yes");
		}
		if (o.getDeliveryDate() != null) {
			userOrdersReport.setDeliveryDate(o.getDeliveryDate());
		}
		if (o.getOrderDate() != null) {
			userOrdersReport.setOrderDate(o.getOrderDate());
		}
		if (o.getTotalAmount() != null) {
			userOrdersReport.setBill(o.getTotalAmount());
		}
		if (o.getUser() != null && o.getUser().getUserName() != null) {
			userOrdersReport.setUserName(o.getUser().getUserName());
		}
		if (o.getOrderId() != null) {
			userOrdersReport.setOrderId(o.getOrderId());
		}
		if (o.getPayment() != null && o.getPayment().getMode() != null) {
			userOrdersReport.setPaymentMode(o.getPayment().getMode());
		}
		return userOrdersReport;
	}

	@Override
	public List<UserOrdersReport> getOrders() {
		List<Order> ordersList = orderRepo.findAll();
		List<UserOrdersReport> orders = ordersList.stream().map(o -> convertOrderToUserOrdersReport(o))
				.collect(Collectors.toList());
		return orders;
}
}
