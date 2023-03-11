package com.app.agri.config;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

@Configuration
public class EmailConfig {
	
	@Autowired
    private JavaMailSender mailSender;
	
	public void sendEmail(String recipientEmail,String token, String username)
	        throws MessagingException, UnsupportedEncodingException //provided by spring
	{
	    MimeMessage message = mailSender.createMimeMessage();              
	    MimeMessageHelper helper = new MimeMessageHelper(message);
	     
	    helper.setFrom("sagarashtekar0007@gmail.com");//from
	    helper.setTo(recipientEmail);//to
	     
	    String subject = "OTP to reset password";//subject
	     
	    //contet in mail
	    String content = "<p>Hello,</p>"+
	    		username
	            + "<p>You have requested to reset your password.</p>"
	            + "<p>Please use below otp to reset your password:</p>"
	            + token
	            + "<br>"
	            + "<p>Ignore this email if you do remember your password, "
	            + "or you have not made the request.</p>";
	     
	    helper.setSubject(subject);
	     
	    helper.setText(content, true);
	     
	    mailSender.send(message);
	}

}
