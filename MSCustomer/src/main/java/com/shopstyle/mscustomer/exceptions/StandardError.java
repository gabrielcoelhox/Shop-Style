package com.shopstyle.mscustomer.exceptions;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class StandardError {

	private Integer status;
	private String error;
	private String message;
	private Instant timestamp;
	private String path;
	
}
