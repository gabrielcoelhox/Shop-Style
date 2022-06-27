package com.shopstyle.mspayment.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.shopstyle.mspayment.dto.InstallmentFormDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Installment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private Integer amount;
	
	private String brand;
	
	@OneToOne
	@JoinColumn(name = "payment_id")
	private Payment payment;
	
	public Installment(InstallmentFormDTO installmentForm, Payment payment) {
		this.amount = installmentForm.getAmount();
		this.brand = installmentForm.getBrand();
		this.payment = payment;
	}
}
