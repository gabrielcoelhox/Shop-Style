package com.shopstyle.mspayment.dto;

import com.shopstyle.mspayment.entities.Installment;
import com.shopstyle.mspayment.entities.Payment;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InstallmentDTO {

	private Long id;
	private Integer amount;
	private String brand;
	private Payment payment;
	
	public InstallmentDTO(Installment inst) {
		this.id = inst.getId();
		this.amount = inst.getAmount();
		this.brand = inst.getBrand();
		this.payment = inst.getPayment();
	}
}
