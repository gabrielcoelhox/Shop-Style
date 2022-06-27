package com.shopstyle.mspayment.dto;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InstallmentFormDTO {

	@NotNull
	private Integer amount;
	
	private String brand;
	
	@NotNull
	private Long paymentId;
}
