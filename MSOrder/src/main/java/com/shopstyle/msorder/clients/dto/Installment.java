package com.shopstyle.msorder.clients.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Installment {

	private Integer amount;	
	private Payment payment;
	private String brand;
}
