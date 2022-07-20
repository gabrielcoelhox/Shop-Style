package com.shopstyle.msaudit.clients.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Payment {

	private Long id;	
	private String type;
	private boolean active;
	private boolean installments;
}
