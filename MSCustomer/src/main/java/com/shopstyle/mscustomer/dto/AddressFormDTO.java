package com.shopstyle.mscustomer.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.shopstyle.mscustomer.enums.State;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressFormDTO {

	@NotNull
	@Enumerated(EnumType.STRING)
	private State state;
	
	@NotNull @NotEmpty
	private String city;
	
	@NotNull @NotEmpty
	private String district;
	
	@NotNull @NotEmpty
	private String street;
	
	@NotNull @NotEmpty
	private String number;
	
	private String complement;
	
	@NotNull @NotEmpty
	private String cep;
	
	@NotNull
	private Long customerId;
}
