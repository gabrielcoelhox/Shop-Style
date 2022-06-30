package com.shopstyle.msorder.clients.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Payment {

	private Long id;	
	private String type;
	private boolean active;
	private boolean installments;
}
