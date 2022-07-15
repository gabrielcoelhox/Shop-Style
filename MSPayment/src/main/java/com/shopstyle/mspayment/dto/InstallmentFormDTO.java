package com.shopstyle.mspayment.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InstallmentFormDTO {

	@NotNull (message = "Amount field cannot be null") 
	private Integer amount;
	
	private String brand;
	
	@NotNull (message = "PaymentId field cannot be null") 
	private Long paymentId;
}
