package com.shopstyle.msorder.controllers;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shopstyle.msorder.dto.OrderDTO;
import com.shopstyle.msorder.dto.OrderFormDTO;
import com.shopstyle.msorder.enums.Status;
import com.shopstyle.msorder.services.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/orders")
@RequiredArgsConstructor
public class OrderController {

	@Autowired
	private final OrderService orderService;
	
	@GetMapping
	public ResponseEntity<List<OrderDTO>> findAll(
			@RequestParam(required = true) LocalDate startDate, 
			@RequestParam(required = false) LocalDate endDate, 
			@RequestParam(required = false) Status status) {
		return new ResponseEntity<>(orderService.findAll(startDate, endDate, status), HttpStatus.OK);
	}
	
	@GetMapping("/customers/{id}")
	public ResponseEntity<List<OrderDTO>> findByCustomerId(@PathVariable Long id, 
			@RequestParam(required = false) LocalDate startDate, 
			@RequestParam(required = false) LocalDate endDate, 
			@RequestParam(required = false) Status status) {
		return new ResponseEntity<>(orderService.findByCustomerId(id, startDate, endDate, status), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<OrderDTO> insert(@RequestBody @Valid OrderFormDTO form) {
		return new ResponseEntity<>(orderService.insert(form), HttpStatus.CREATED);
	}
}
