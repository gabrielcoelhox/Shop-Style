package com.shopstyle.msorder.rabbitmq.consumer;

import java.io.Serializable;

import com.shopstyle.msorder.enums.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentOrderStatus implements Serializable {
	private static final long serialVersionUID = 1L;

	private Status status;
	private String orderId;
}
