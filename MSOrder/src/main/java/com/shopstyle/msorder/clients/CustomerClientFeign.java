package com.shopstyle.msorder.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.shopstyle.msorder.clients.dto.Address;
import com.shopstyle.msorder.clients.dto.Customer;

@Component
@FeignClient("customer")
public interface CustomerClientFeign {

	@GetMapping("/v1/customers/{id}")
	Customer getCustomer(@PathVariable Long id);
	
	@GetMapping("/v1/addresses/{id}")
	Address getAddress(@PathVariable Long id);
}
