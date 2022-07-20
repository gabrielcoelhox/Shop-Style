package com.shopstyle.msaudit.services;

import com.shopstyle.msaudit.dto.OrderDTO;
import com.shopstyle.msaudit.entities.Order;

public interface AuditService {

	OrderDTO insert(Order order);

	OrderDTO findById(String id);
}
