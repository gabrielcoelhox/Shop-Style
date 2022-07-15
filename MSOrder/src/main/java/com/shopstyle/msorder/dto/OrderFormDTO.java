package com.shopstyle.msorder.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderFormDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private CustomerDTO customer;
	private PaymentDTO payment;
	private List<CartDTO> cart;
}
