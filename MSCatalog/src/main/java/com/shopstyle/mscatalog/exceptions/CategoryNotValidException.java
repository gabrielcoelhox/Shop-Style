package com.shopstyle.mscatalog.exceptions;

public class CategoryNotValidException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public CategoryNotValidException(String msg) {
		super(msg);
	}
}
