package com.shopstyle.msorder.entities;

import java.time.LocalDate;
import java.util.List;

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
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate date;
	private Status status;
	private Double total;

	private Customer customer;
	private Address address;
	private Payment payment;
	private Installment installment;
	private List<Sku> cart;
}
