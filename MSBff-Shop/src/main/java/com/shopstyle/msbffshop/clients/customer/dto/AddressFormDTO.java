package com.shopstyle.msbffshop.clients.customer.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.shopstyle.msbffshop.clients.enums.State;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddressFormDTO {

	@NotNull (message = "State field cannot be null")
	private State state;
	
	@NotNull (message = "city field cannot be null") 
	@NotEmpty
	private String city;
	
	@NotNull (message = "district field cannot be null")
	@NotEmpty
	private String district;
	
	@NotNull (message = "street field cannot be null")
	@NotEmpty
	private String street;
	
	@NotNull (message = "number field cannot be null")
	@NotEmpty
	private String number;
	
	private String complement;
	
	@NotNull (message = "cep field cannot be null")
	@NotEmpty
	private String cep;
	
	@NotNull (message = "customerId field cannot be null")
	private Long customerId;
}
