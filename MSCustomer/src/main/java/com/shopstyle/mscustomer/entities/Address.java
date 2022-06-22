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
import com.shopstyle.mscustomer.enums.State;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_address")
@Getter
@Setter
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

}
