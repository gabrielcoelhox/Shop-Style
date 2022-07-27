package com.shopstyle.msaudit.services;

import org.springframework.stereotype.Service;

import com.shopstyle.msaudit.dto.OrderDTO;
import com.shopstyle.msaudit.entities.Order;
import com.shopstyle.msaudit.exceptions.DefaultException;
import com.shopstyle.msaudit.repository.AuditRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuditServiceImpl implements AuditService {
	
	private final AuditRepository auditRepository;

	@Override
	public final OrderDTO insert(Order order) {
		return new OrderDTO(auditRepository.save(order));
	}

	@Override
	public final OrderDTO findById(String id) {
		return new OrderDTO(auditRepository.findById(id).orElseThrow(
				() -> new DefaultException("Order with ID: " + id + " not found.", "NOT_FOUND", 404)));
	}
}
