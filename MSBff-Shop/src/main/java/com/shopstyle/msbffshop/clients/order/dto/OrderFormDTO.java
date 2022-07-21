package com.shopstyle.msbffshop.clients.order.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderFormDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@NotNull (message = "customer field cannot be null")
	private CustomerDTO customer;
	
	@NotNull (message = "payment field cannot be null")
	private PaymentDTO payment;
	
	@NotNull (message = "cart field cannot be null")
	private List<CartDTO> cart;
}