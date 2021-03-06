package com.shopstyle.mspayment.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.shopstyle.mspayment.dto.PaymentFormDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull @NotEmpty
	private String type;
	
	@NotNull
	private boolean active;
	
	@NotNull
	private boolean installments;
	
	public Payment(PaymentFormDTO form) {
		this.type = form.getType();
		this.active = form.isActive();
		this.installments = form.isInstallments();
	}
}
