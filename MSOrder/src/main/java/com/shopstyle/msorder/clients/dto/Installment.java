package com.shopstyle.msorder.clients.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Installment {

	private Integer amount;	
	private Payment payment;
	private String brand;
}
