package com.shopstyle.mspayment.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.shopstyle.mspayment.dto.InstallmentFormDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Installment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private Integer amount;
	
	private String brand;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "payment_id", unique = true)
	private Payment payment;
	
	public Installment(InstallmentFormDTO form, Payment payment) {
		this.amount = form.getAmount();
		this.brand = form.getBrand();
		this.payment = payment;
	}
}
