package com.shopstyle.msbffshop.clients.entities;

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
