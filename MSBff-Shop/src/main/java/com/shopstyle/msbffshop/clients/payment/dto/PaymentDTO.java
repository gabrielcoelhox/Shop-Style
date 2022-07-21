package com.shopstyle.msbffshop.clients.payment.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaymentDTO {

	private Long id;
	private String type;
	private boolean active;
	private boolean installments;
}
