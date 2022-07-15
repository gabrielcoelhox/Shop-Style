package com.shopstyle.mspayment.dto;

import com.shopstyle.mspayment.entities.Payment;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaymentDTO {

	private Long id;
	private String type;
	private boolean active;
	private boolean installments;
	
	public PaymentDTO(Payment pay) {
		this.id = pay.getId();
		this.type = pay.getType();
		this.active = pay.isActive();
		this.installments = pay.isInstallments();
	}
}
