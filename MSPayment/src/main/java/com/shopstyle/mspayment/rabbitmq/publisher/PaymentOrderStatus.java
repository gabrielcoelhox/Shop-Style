package com.shopstyle.mspayment.rabbitmq.publisher;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentOrderStatus implements Serializable {
	private static final long serialVersionUID = 1L;

	private String orderId;
	private Status status;
}
