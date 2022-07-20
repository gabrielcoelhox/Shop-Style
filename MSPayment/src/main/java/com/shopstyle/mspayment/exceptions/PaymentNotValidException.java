package com.shopstyle.mspayment.exceptions;

public class PaymentNotValidException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public PaymentNotValidException(String msg) {
		super(msg);
	}
}
