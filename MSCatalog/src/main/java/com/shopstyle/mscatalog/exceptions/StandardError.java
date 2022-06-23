package com.shopstyle.mscatalog.exceptions;

import java.time.Instant;

import lombok.Data;

@Data
public class StandardError {

	private Integer status;
	private String error;
	private String message;
	private Instant timestamp;
	private String path;
}
