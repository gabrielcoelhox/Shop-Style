package com.shopstyle.mscustomer.controllers;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/address")
@RequiredArgsConstructor
public class AddressController {

	@Autowired
	private AddressService addressService;
	
	@Transactional
	@PostMapping
	@ApiOperation(value = "Insert a new Address")
	public ResponseEntity<AddressDTO> save(@RequestBody @Valid AddressFormDTO addressFormDto){
		return new ResponseEntity<AddressDTO>(addressService.save(addressFormDto), HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<AddressDTO> update(@PathVariable Long id, @RequestBody @Valid AddressFormDTO addressFormDto){
		return new ResponseEntity<AddressDTO>(addressService.update(id, addressFormDto), HttpStatus.OK);
	}
}
