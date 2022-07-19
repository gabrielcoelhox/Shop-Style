package com.shopstyle.mspayment.rabbitmq.consumer;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaymentOrder implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String orderId;
	private PaymentRabbitDTO payment;
}