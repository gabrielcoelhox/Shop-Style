package com.shopstyle.msbffshop.clients.order.dto;

import java.time.LocalDate;
import java.util.List;

import com.shopstyle.msbffshop.clients.catalog.dto.SkuDTO;
import com.shopstyle.msbffshop.clients.customer.dto.AddressDTO;
import com.shopstyle.msbffshop.clients.entities.Installment;
import com.shopstyle.msbffshop.clients.enums.Status;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderDTO {

	private String id;	
	private CustomerDTO customer;
	private List<SkuDTO> cart;
	private PaymentDTO payment;
	private Installment installment;
	private AddressDTO address;
	private Double total;
	private LocalDate date;
	private Status status;
}
