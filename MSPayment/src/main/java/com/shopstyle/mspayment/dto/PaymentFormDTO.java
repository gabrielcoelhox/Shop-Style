package com.shopstyle.mspayment.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentFormDTO {

	@NotNull (message = "Type field cannot be null") 
	@NotEmpty
	private String type;
	
	@NotNull
	private boolean active;
	
	@NotNull
	private boolean installments;
}
