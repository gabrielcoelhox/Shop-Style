package com.shopstyle.mscustomer.controllers;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.shopstyle.mscustomer.dto.CustomerDTO;
import com.shopstyle.mscustomer.dto.CustomerFormDTO;
import com.shopstyle.mscustomer.entities.Customer;
import com.shopstyle.mscustomer.services.CustomerService;
import com.shopstyle.mscustomer.services.CustomerServiceImpl;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class CustomerController {

	@Autowired
	private final CustomerServiceImpl customerServiceImpl;
	
	@Autowired
	private CustomerService customerService;
	
	private final PasswordEncoder passwordEncoder;
	
	@ApiOperation(value= "Return all users")
	@GetMapping
	public ResponseEntity<List<CustomerDTO>> findAll() {
		List<CustomerDTO> listUsuarioDTO = customerService.findAll();
		return ResponseEntity.ok().body(listUsuarioDTO);
	}
	
	@ApiOperation(value= "Returns a unique user by id")	
	@GetMapping("/customers/{id}")
	public ResponseEntity<CustomerDTO> findById(@PathVariable Long id) {
		return new ResponseEntity<>(customerService.findById(id), HttpStatus.OK);
	}
	
	@Transactional
	@PostMapping("/customers")
	@ApiOperation(value = "Insert a new Customer")
    @ResponseStatus(HttpStatus.CREATED)
    public Customer save(@RequestBody @Valid Customer user){
        String senhaCriptografada = passwordEncoder.encode(user.getPassword());
        user.setPassword(senhaCriptografada);
        return customerServiceImpl.save(user);
    }
	
	@ApiOperation(value= "Update a customer")
	@ApiResponses({
	      @ApiResponse(code = 200, message = "Update done successfully",
	            response = CustomerDTO.class),
	      @ApiResponse(code = 403, message = "Profile not authorized to perform this operation",
			response = CustomerDTO.class),
	      @ApiResponse(code = 404, message = "Customer not found",
	            response = CustomerDTO.class)
	})
	@PutMapping("/customers/{id}")
	@Transactional
	public ResponseEntity<CustomerDTO> update(@RequestBody @Valid CustomerFormDTO customerFormDto, @PathVariable Long id) {
		return new ResponseEntity<>(customerService.update(customerFormDto, id), HttpStatus.OK);
	}
}
