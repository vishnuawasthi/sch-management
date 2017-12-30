package com.sch.mngt.exception;

public class InvalidCredentialException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4421617907540358194L;
	private String message;

	public InvalidCredentialException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
	

}
