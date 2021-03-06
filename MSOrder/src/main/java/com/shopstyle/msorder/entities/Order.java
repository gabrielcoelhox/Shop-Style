package com.shopstyle.msorder.entities;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shopstyle.msorder.clients.dto.Address;
import com.shopstyle.msorder.clients.dto.Customer;
import com.shopstyle.msorder.clients.dto.Installment;
import com.shopstyle.msorder.clients.dto.Payment;
import com.shopstyle.msorder.clients.dto.Sku;
import com.shopstyle.msorder.enums.Status;

import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@NoArgsConstructor
public class Order {

	@Id
	private String id;
	
	@NotNull
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate date;
	
	@NotNull
	private Status status;
	
	@NotNull
	private Double total;

	@NotNull
	private Customer customer;
	
	@NotNull
	private Address address;
	
	@NotNull
	private Payment payment;
	
	@NotNull
	private Installment installment;
	
	@NotNull
	private List<Sku> cart;
}
