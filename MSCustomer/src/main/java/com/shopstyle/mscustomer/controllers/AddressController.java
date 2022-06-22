package com.shopstyle.mscustomer.controllers;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopstyle.mscustomer.dto.AddressDTO;
import com.shopstyle.mscustomer.dto.AddressFormDTO;
import com.shopstyle.mscustomer.services.AddressService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/v1/address")
public class AddressController {

	@Autowired
	private AddressService addressService;
	
	@Transactional
	@PostMapping
	@ApiOperation(value = "Insert a new Address")
	public ResponseEntity<AddressDTO> insert(@RequestBody @Valid AddressFormDTO addressFormDto) {
		return new ResponseEntity<>(addressService.insert(addressFormDto), HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	@ApiOperation(value = "Update a Address")
	public ResponseEntity<AddressDTO> update(@PathVariable Long id, @RequestBody @Valid AddressFormDTO addressFormDto) {
		return new ResponseEntity<>(addressService.update(id, addressFormDto), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value = "Delete by Id")
	public ResponseEntity<AddressDTO> deleteById(@PathVariable Long id) {
		addressService.deleteById(id); 
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
