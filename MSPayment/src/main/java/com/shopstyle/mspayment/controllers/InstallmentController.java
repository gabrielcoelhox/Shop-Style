package com.shopstyle.mspayment.controllers;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopstyle.mspayment.dto.InstallmentDTO;
import com.shopstyle.mspayment.dto.InstallmentFormDTO;
import com.shopstyle.mspayment.services.InstallmentService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/installments")
@RequiredArgsConstructor
public class InstallmentController {

	private final InstallmentService installmentService;
	
	@PostMapping
	@Transactional
	@ApiOperation(value = "Insert a new installment")
	public ResponseEntity<InstallmentDTO> insert(@RequestBody @Valid InstallmentFormDTO form){
		return new ResponseEntity<>(installmentService.insert(form), HttpStatus.CREATED);
	}
	
	@ApiResponses({
	      @ApiResponse(code = 200, message = "Update done successfully", response = InstallmentDTO.class),
	      @ApiResponse(code = 403, message = "Profile not authorized to perform this operation", response = InstallmentDTO.class),
	      @ApiResponse(code = 404, message = "Installment not found", response = InstallmentDTO.class)
	})
	@PutMapping("/{id}")
	@Transactional
	@ApiOperation(value = "Update a installment")
	public ResponseEntity<InstallmentDTO> update(@PathVariable Long id, @RequestBody @Valid InstallmentFormDTO form){
		return new ResponseEntity<>(installmentService.update(id, form), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	@ApiOperation(value = "Delete a installment")
	public ResponseEntity<Void> deleteById(@PathVariable Long id){
		installmentService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
