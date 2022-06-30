package com.shopstyle.msorder.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderFormDTO {

	private CustomerDTO customer;
	private PaymentDTO payment;
	private List<CartDTO> cart;
}
