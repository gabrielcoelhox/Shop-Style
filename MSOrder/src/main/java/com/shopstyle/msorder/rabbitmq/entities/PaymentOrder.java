package com.shopstyle.msorder.rabbitmq.entities;

import java.io.Serializable;

import com.shopstyle.msorder.dto.PaymentDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaymentOrder implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String orderId;
	private PaymentDTO payment;
}
