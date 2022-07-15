package com.shopstyle.mscustomer.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shopstyle.mscustomer.dto.AddressFormDTO;
import com.shopstyle.mscustomer.enums.State;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_address")
@Data
@NoArgsConstructor
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Length(min = 2, max = 2)
	@Enumerated(EnumType.STRING)
	private State state;
	
	@NotNull @NotEmpty
	private String city;
	
	@NotNull @NotEmpty
	private String district;
	
	@NotNull @NotEmpty
	private String street;
	
	@NotNull @NotEmpty
	private String number;
	
	@NotNull @NotEmpty
	private String cep;
	
	private String complement;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "customer_id")
	private Customer customer;

	public Address(AddressFormDTO form, Customer customer) {
		this.street = form.getStreet();
		this.number = form.getNumber();
		this.complement = form.getComplement();
		this.district = form.getDistrict();
		this.city = form.getCity();
		this.state = form.getState();
		this.cep = form.getCep();
		this.customer = customer;
	}
}
