package com.shopstyle.mscustomer.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class DefaultException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	private String error;
	private String message;
	private Integer status;
	
	public DefaultException(String error, String message, Integer status) {
		this.error = error;
		this.message = message;
		this.status = status;
	}
}
