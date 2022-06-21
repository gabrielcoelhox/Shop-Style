package com.shopstyle.mscustomer.dto;

import com.shopstyle.mscustomer.entities.Address;
import com.shopstyle.mscustomer.enums.State;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddressDTO {

	private Long id;
	private State state;
	private String city;
	private String district;
	private String street;
	private String number;
	private String complement;
	private String cep;
	private CustomerDTO customerDTO;
	
	public AddressDTO(Address address) {
		this.id = address.getId();
		this.street = address.getStreet();
		this.number = address.getNumber();
		this.complement = address.getComplement();
		this.district = address.getDistrict();
		this.city = address.getCity();
		this.state = address.getState();
		this.cep = address.getCep();	
		this.customerDTO = new CustomerDTO(address.getCustomer());
	}
}
