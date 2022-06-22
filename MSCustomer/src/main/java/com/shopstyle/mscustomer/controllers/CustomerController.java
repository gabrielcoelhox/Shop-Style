package com.shopstyle.mscustomer.controllers;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.shopstyle.mscustomer.dto.CustomerChangePasswordDTO;
import com.shopstyle.mscustomer.dto.CustomerDTO;
import com.shopstyle.mscustomer.dto.CustomerFormDTO;
import com.shopstyle.mscustomer.dto.CustomerLoginDTO;
import com.shopstyle.mscustomer.services.CustomerService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@ApiOperation(value= "Return all users")
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<CustomerDTO>> findAll() {
		List<CustomerDTO> listCustomerDTO = customerService.findAll();
		return ResponseEntity.ok().body(listCustomerDTO);
	}
	
	@ApiOperation(value= "Returns a unique user by id")	
	@GetMapping("/customers/{id}")
	public ResponseEntity<CustomerDTO> findById(@PathVariable Long id) {
		return new ResponseEntity<>(customerService.findById(id), HttpStatus.OK);
	}
	
	@Transactional
	@PostMapping("/customers")
	@ApiOperation(value = "Insert a new Customer")
	public ResponseEntity<CustomerDTO> insert(@RequestBody @Valid CustomerFormDTO customerFormDto) {
		return new ResponseEntity<>(customerService.insert(customerFormDto), HttpStatus.CREATED);
	}
	
	@ApiOperation(value= "Update a customer")
	@ApiResponses({
	      @ApiResponse(code = 200, message = "Update done successfully", response = CustomerDTO.class),
	      @ApiResponse(code = 403, message = "Profile not authorized to perform this operation", response = CustomerDTO.class),
	      @ApiResponse(code = 404, message = "Customer not found", response = CustomerDTO.class)
	})
	@Transactional
	@PutMapping("/customers/{id}")
	public ResponseEntity<CustomerDTO> update(@RequestBody @Valid CustomerFormDTO customerFormDto, @PathVariable Long id) {
		return new ResponseEntity<>(customerService.update(customerFormDto, id), HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public ResponseEntity<CustomerDTO> login(@RequestBody @Valid CustomerLoginDTO customerLoginDto) {
		return new ResponseEntity<>(customerService.login(customerLoginDto), HttpStatus.ACCEPTED);
	}
	
	@Transactional
	@PutMapping("/customers/{id}/password")
	public ResponseEntity<CustomerDTO> changePassword(@RequestBody @Valid CustomerChangePasswordDTO passwordDto, @PathVariable Long id) {
		return new ResponseEntity<>(customerService.changePassword(passwordDto, id), HttpStatus.ACCEPTED);
	}
}
