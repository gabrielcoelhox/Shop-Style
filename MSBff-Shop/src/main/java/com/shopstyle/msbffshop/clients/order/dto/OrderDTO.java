package com.shopstyle.msbffshop.clients.order.dto;

import java.time.LocalDate;
import java.util.List;

import com.shopstyle.msbffshop.clients.entities.Address;
import com.shopstyle.msbffshop.clients.entities.Customer;
import com.shopstyle.msbffshop.clients.entities.Installment;
import com.shopstyle.msbffshop.clients.entities.Payment;
import com.shopstyle.msbffshop.clients.entities.Sku;
import com.shopstyle.msbffshop.clients.enums.Status;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderDTO {

	private String id;	
	private Customer customer;
	private List<Sku> cart;
	private Payment payment;
	private Installment installment;
	private Address address;
	private Double total;
	private LocalDate date;
	private Status status;
}
