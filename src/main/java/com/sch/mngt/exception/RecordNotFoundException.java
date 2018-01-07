package com.sch.mngt.exception;

public class RecordNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7045234954502358700L;
	
	private String message;

	public RecordNotFoundException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
	

}
