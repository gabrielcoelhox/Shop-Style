package com.shopstyle.msorder.clients.dto;

import com.shopstyle.msorder.clients.enums.State;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Address {

	private Long id;
	private State state;
	private String city;
	private String district;
	private String street;
	private String number;
	private String complement;
	private String cep;
}
