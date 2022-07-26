package com.shopstyle.mscustomer.services;

import java.util.List;

import javax.validation.Valid;

import com.shopstyle.mscustomer.dto.CustomerChangePasswordDTO;
import com.shopstyle.mscustomer.dto.CustomerDTO;
import com.shopstyle.mscustomer.dto.CustomerFormDTO;
import com.shopstyle.mscustomer.dto.CustomerLoginDTO;
import com.shopstyle.mscustomer.dto.CustomerUpdateDTO;
import com.shopstyle.mscustomer.entities.Customer;

public interface CustomerService {

	List<CustomerDTO> findAll();
	
	CustomerDTO findById(Long id);
	
	Customer findByEmail(String email);

	CustomerDTO insert(@Valid CustomerFormDTO userForm);

	CustomerDTO update(@Valid CustomerUpdateDTO userForm, Long id);

	CustomerDTO login(CustomerLoginDTO customerLoginDto);

	CustomerDTO changePassword(@Valid CustomerChangePasswordDTO passwordDto, Long id);
}
