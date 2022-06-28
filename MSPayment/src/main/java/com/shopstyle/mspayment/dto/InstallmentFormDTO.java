package com.shopstyle.mspayment.dto;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InstallmentFormDTO {

	@NotNull (message = "Amount field cannot be null") 
	private Integer amount;
	
	private String brand;
	
	@NotNull (message = "PaymentId field cannot be null") 
	private Long paymentId;
}
