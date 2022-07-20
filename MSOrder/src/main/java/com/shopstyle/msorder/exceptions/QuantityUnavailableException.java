package com.shopstyle.msorder.exceptions;

public class QuantityUnavailableException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public QuantityUnavailableException(String msg) {
		super(msg);
	}
}
