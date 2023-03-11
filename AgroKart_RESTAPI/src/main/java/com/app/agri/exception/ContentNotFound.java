package com.app.agri.exception;
//Custom Exception to handle all generic NotFoundException
public class ContentNotFound extends Exception {

	private String messege;

	public ContentNotFound(String messege) {
		super(messege);
		
	}

}
