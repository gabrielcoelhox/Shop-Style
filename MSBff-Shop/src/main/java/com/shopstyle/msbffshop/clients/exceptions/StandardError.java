package com.shopstyle.msbffshop.clients.exceptions;

import java.time.Instant;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StandardError {

	private Integer status;
	private String error;
	private String message;
	private Instant timestamp;
	private String path;

}
