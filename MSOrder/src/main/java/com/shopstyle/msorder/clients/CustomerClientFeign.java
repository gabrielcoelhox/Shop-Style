package com.shopstyle.msorder.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shopstyle.msorder.clients.dto.Address;
import com.shopstyle.msorder.clients.dto.Customer;

@Component
@FeignClient("customer")
public interface CustomerClientFeign {

	@RequestMapping("/v1/customers/{id}")
	Customer getCustomer(@PathVariable Long id);
	
	@RequestMapping("/v1/addresses/{id}")
	Address getAddress(@PathVariable Long id);
}
