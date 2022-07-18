package com.shopstyle.msorder.services;

import java.time.LocalDate;
import java.util.List;

import com.shopstyle.msorder.dto.OrderDTO;
import com.shopstyle.msorder.dto.OrderFormDTO;
import com.shopstyle.msorder.enums.Status;
import com.shopstyle.msorder.rabbitmq.consumer.PaymentOrderStatus;

public interface OrderService {

	List<OrderDTO> findAll();
	
	List<OrderDTO> findByCustomerId(Long id, LocalDate startDate, LocalDate endDate, Status status);
	
	OrderDTO insert(OrderFormDTO orderForm);
	
	OrderDTO updateStatusPayment(PaymentOrderStatus paymentOrderStatus);
}
