package com.shopstyle.mscustomer.exceptions;

public class InvalidPasswordException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public InvalidPasswordException() {
        super("Invalid Password");
    }
}
