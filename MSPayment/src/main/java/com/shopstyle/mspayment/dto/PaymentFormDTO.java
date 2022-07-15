package com.shopstyle.mspayment.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaymentFormDTO {

	@NotNull (message = "Type field cannot be null") 
	@NotEmpty
	private String type;
	
	@NotNull
	private boolean active;
	
	@NotNull
	private boolean installments;
}
