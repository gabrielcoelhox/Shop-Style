package com.shopstyle.mspayment.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopstyle.mspayment.dto.PaymentDTO;
import com.shopstyle.mspayment.dto.PaymentFormDTO;
import com.shopstyle.mspayment.services.PaymentServiceImpl;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/payments")
@RequiredArgsConstructor
public class PaymentController {

	private final PaymentServiceImpl paymentServiceImpl;

	@GetMapping
	@ApiOperation(value = "Find all payments")
	public ResponseEntity<List<PaymentDTO>> findAll() {
		return new ResponseEntity<>(paymentServiceImpl.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	@ApiOperation(value = "Find a payment by id")
	public ResponseEntity<PaymentDTO> findById(@PathVariable Long id) {
		return new ResponseEntity<>(paymentServiceImpl.findById(id), HttpStatus.OK);
	}
	
	@PostMapping
	@Transactional
	@ApiOperation(value = "Insert a new payment")
	public ResponseEntity<PaymentDTO> insert(@RequestBody @Valid PaymentFormDTO paymentForm){
		return new ResponseEntity<>(paymentServiceImpl.insert(paymentForm), HttpStatus.CREATED);
	}
	
	@ApiResponses({
	      @ApiResponse(code = 200, message = "Update done successfully", response = PaymentDTO.class),
	      @ApiResponse(code = 403, message = "Profile not authorized to perform this operation", response = PaymentDTO.class),
	      @ApiResponse(code = 404, message = "Payment not found", response = PaymentDTO.class)
	})
	@PutMapping("/{id}")
	@Transactional
	@ApiOperation(value = "Update a payment")
	public ResponseEntity<PaymentDTO> update(@PathVariable Long id, @RequestBody @Valid PaymentFormDTO paymentForm){
		return new ResponseEntity<>(paymentServiceImpl.update(id, paymentForm), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	@ApiOperation(value = "Delete a payment")
	public ResponseEntity<Void> deleteById(@PathVariable Long id){
		paymentServiceImpl.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
