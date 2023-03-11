package com.app.agri.exception;

public class AlreadyExistsException extends Exception {

	private String messege;

	public AlreadyExistsException(String messege) {
		super(messege);
		
	}
}
