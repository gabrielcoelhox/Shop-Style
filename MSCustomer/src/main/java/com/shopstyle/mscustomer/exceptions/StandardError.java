package com.shopstyle.mscustomer.exceptions;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StandardError {

	private Integer status;
	private String error;
	private String message;
	private Instant timestamp;
	//private String path;
	
}
