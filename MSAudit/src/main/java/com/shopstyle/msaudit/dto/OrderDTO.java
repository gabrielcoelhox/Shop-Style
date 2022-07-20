package com.shopstyle.msaudit.dto;

import java.time.LocalDate;
import java.util.List;

import com.shopstyle.msaudit.clients.dto.Address;
import com.shopstyle.msaudit.clients.dto.Customer;
import com.shopstyle.msaudit.clients.dto.Installment;
import com.shopstyle.msaudit.clients.dto.Payment;
import com.shopstyle.msaudit.clients.dto.Sku;
import com.shopstyle.msaudit.entities.Order;
import com.shopstyle.msaudit.enums.Status;

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
	
	public OrderDTO(Order order) {
		this.id = order.getId();
		this.customer = order.getCustomer();
		this.payment = order.getPayment();
		this.cart = order.getCart();
		this.date = order.getDate();
		this.total = order.getTotal();
		this.status = order.getStatus();
		this.address = order.getAddress();
		this.installment = order.getInstallment();
	}
}
