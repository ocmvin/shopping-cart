package com.ecart.customer.exception;

public class RecordNotFound extends RuntimeException {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3949549329093672022L;


	public RecordNotFound() {
		super();
	}
	
	
	public RecordNotFound(String message) {
		super(message);
	}
	
}
