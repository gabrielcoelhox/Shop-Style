package com.shopstyle.mscustomer.services;

import javax.validation.Valid;

import com.shopstyle.mscustomer.dto.AddressDTO;
import com.shopstyle.mscustomer.dto.AddressFormDTO;

public interface AddressService {

	AddressDTO findById(Long id);
	
	AddressDTO insert(@Valid AddressFormDTO addressForm);

	AddressDTO update(Long id, @Valid AddressFormDTO addressForm);

	void deleteById(Long id);
}
