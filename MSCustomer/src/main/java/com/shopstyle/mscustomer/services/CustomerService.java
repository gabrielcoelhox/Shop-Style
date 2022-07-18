package com.shopstyle.mscustomer.services;

import javax.validation.Valid;

import com.shopstyle.mscustomer.dto.CustomerChangePasswordDTO;
import com.shopstyle.mscustomer.dto.CustomerDTO;
import com.shopstyle.mscustomer.dto.CustomerFormDTO;
import com.shopstyle.mscustomer.dto.CustomerLoginDTO;

public interface CustomerService {

	CustomerDTO findById(Long id);

	CustomerDTO insert(@Valid CustomerFormDTO userForm);

	CustomerDTO update(@Valid CustomerFormDTO userForm, Long id);

	CustomerDTO login(CustomerLoginDTO customerLoginDto);

	CustomerDTO changePassword(@Valid CustomerChangePasswordDTO passwordDto, Long id);
}
