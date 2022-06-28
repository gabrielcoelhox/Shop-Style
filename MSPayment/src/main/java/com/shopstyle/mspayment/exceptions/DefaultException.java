package com.shopstyle.mspayment.exceptions;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DefaultException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private final String error;
	private final String message;
	private final Integer status;
	
	public DefaultException(String error, String message, Integer status) {
		this.error = error;
		this.message = message;
		this.status = status;
	}
}
