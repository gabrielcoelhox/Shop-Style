package com.shopstyle.msorder.services;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import com.shopstyle.msorder.dto.OrderDTO;
import com.shopstyle.msorder.dto.OrderFormDTO;
import com.shopstyle.msorder.enums.Status;
import com.shopstyle.msorder.rabbitmq.consumer.PaymentOrderStatus;

public interface OrderService {

	List<OrderDTO> findAll(LocalDate startDate, LocalDate endDate, Status status);
	
	List<OrderDTO> findByCustomerId(Long id, LocalDate startDate, LocalDate endDate, Status status);
	
	OrderDTO insert(@Valid OrderFormDTO orderForm);
	
	OrderDTO updateStatusPayment(PaymentOrderStatus paymentOrderStatus);
}
