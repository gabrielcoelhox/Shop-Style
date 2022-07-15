package com.shopstyle.msorder.dto;

import java.time.LocalDate;
import java.util.List;

import com.shopstyle.msorder.clients.dto.Address;
import com.shopstyle.msorder.clients.dto.Customer;
import com.shopstyle.msorder.clients.dto.Installment;
import com.shopstyle.msorder.clients.dto.Payment;
import com.shopstyle.msorder.clients.dto.Sku;
import com.shopstyle.msorder.entities.Order;
import com.shopstyle.msorder.enums.Status;

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
