package com.shopstyle.msbffshop.clients.order.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaymentFormDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@NotNull
	private Long id;
	
	@NotNull
	private Integer installments;
}
