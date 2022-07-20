package com.shopstyle.msaudit.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopstyle.msaudit.dto.OrderDTO;
import com.shopstyle.msaudit.entities.Order;
import com.shopstyle.msaudit.services.AuditService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/audit")
@RequiredArgsConstructor
public class AuditController {

	private final AuditService auditService;
	
	@GetMapping("/orders/{id}")
	public ResponseEntity<OrderDTO> findBYid(@PathVariable String id) {
		return new ResponseEntity<>(auditService.findById(id), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<OrderDTO> insert(@RequestBody Order orderInsert){
		return new ResponseEntity<>(auditService.insert(orderInsert), HttpStatus.CREATED);
	}
}
