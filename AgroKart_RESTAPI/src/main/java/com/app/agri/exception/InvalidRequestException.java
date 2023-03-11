package com.app.agri.exception;

public class InvalidRequestException extends Exception {

	private String messege;

	public InvalidRequestException(String messege) {
		super(messege);
		
	}
}
