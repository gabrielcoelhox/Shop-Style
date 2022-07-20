package com.shopstyle.msaudit.entities;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shopstyle.msaudit.clients.dto.Address;
import com.shopstyle.msaudit.clients.dto.Customer;
import com.shopstyle.msaudit.clients.dto.Installment;
import com.shopstyle.msaudit.clients.dto.Payment;
import com.shopstyle.msaudit.clients.dto.Sku;
import com.shopstyle.msaudit.enums.Status;

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
