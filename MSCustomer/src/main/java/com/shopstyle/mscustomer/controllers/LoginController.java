package com.shopstyle.mscustomer.controllers;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopstyle.mscustomer.dto.CustomerDTO;
import com.shopstyle.mscustomer.dto.CustomerLoginDTO;
import com.shopstyle.mscustomer.services.CustomerService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/login")
@RequiredArgsConstructor
public class LoginController {

	private CustomerService customerService;
	
	@PostMapping
	@ApiOperation(value= "Login")	
	public ResponseEntity<CustomerDTO> login(@RequestBody @Valid CustomerLoginDTO loginDTO) {
		return new ResponseEntity<>(customerService.login(loginDTO), HttpStatus.ACCEPTED);
	}
}