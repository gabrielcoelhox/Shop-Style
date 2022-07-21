package com.shopstyle.msbffshop.clients.customer.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.shopstyle.msbffshop.clients.enums.State;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AddressDTO {

	private Long id;
	private State state;
	private String city;
	private String district;
	private String street;
	private String number;
	private String complement;
	private String cep;

}
