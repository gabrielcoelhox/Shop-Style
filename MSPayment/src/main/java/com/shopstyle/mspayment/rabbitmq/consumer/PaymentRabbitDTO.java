package com.shopstyle.mspayment.rabbitmq.consumer;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaymentRabbitDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer installments;
	private Long id;
}
