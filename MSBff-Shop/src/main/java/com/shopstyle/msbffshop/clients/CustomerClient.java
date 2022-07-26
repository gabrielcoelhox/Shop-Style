package com.shopstyle.msbffshop.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.shopstyle.msbffshop.clients.customer.dto.AddressDTO;
import com.shopstyle.msbffshop.clients.customer.dto.AddressFormDTO;
import com.shopstyle.msbffshop.clients.customer.dto.CustomerChangePasswordDTO;
import com.shopstyle.msbffshop.clients.customer.dto.CustomerDTO;
import com.shopstyle.msbffshop.clients.customer.dto.CustomerFormDTO;
import com.shopstyle.msbffshop.clients.customer.dto.CustomerLoginDTO;
import com.shopstyle.msbffshop.clients.entities.Customer;

@Component
@FeignClient("customer")
public interface CustomerClient {

	@PostMapping("/v1/login")
	CustomerDTO loginCustomer(@RequestBody CustomerLoginDTO customerLoginDto);
	
	@PostMapping("/v1/customers")
	CustomerDTO saveCustomer(@RequestBody CustomerFormDTO customerFormDto);
	
	@GetMapping("/v1/customers/{id}")
	CustomerDTO findCustomerById(@PathVariable Long id);
	
	@GetMapping("/v1/customers")
	Customer findCustomerByEmail(@RequestParam(required = true) String email);
	
	@PutMapping("/v1/customers/{id}")
	CustomerDTO updateCustomerById(@PathVariable Long id, @RequestBody CustomerFormDTO customerFormDto);
	
	@PutMapping("/v1/customers/{id}/password")
	CustomerDTO changePasswordCustomer(@RequestBody CustomerChangePasswordDTO passwordDto, @PathVariable Long id);
	
	@PostMapping("/v1/addresses")
	AddressDTO saveAddress(@RequestBody AddressFormDTO addressFormDto);

	@PutMapping("/v1/addresses/{id}")
	AddressDTO updateAddressById(@PathVariable Long id, @RequestBody AddressFormDTO addressFormDto);
	
	@DeleteMapping("/v1/addresses/{id}")
	void deleteAddressById(@PathVariable Long id);
}