package com.shopstyle.msbffshop.clients.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Installment {

	private Integer amount;	
	private Payment payment;
	private String brand;
}
