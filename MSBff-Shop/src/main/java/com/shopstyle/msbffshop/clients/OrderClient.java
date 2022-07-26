package com.shopstyle.msbffshop.clients;

import java.time.LocalDate;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.shopstyle.msbffshop.clients.enums.Status;
import com.shopstyle.msbffshop.clients.order.dto.OrderDTO;
import com.shopstyle.msbffshop.clients.order.dto.OrderFormDTO;

@Component
@FeignClient("order")
public interface OrderClient {
	
	@PostMapping("/v1/orders")
	OrderDTO saveOrder(@RequestBody OrderFormDTO formDTO);

	@GetMapping("/v1/orders/customers/{customerId}")
	List<OrderDTO> findOrdersByCustomerId(@PathVariable Long customerId, @RequestParam(required = false) LocalDate startDate, 
			@RequestParam(required = false) LocalDate endDate, @RequestParam(required = false) Status status);

}