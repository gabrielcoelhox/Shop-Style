package com.shopstyle.msorder.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@NotNull (message = "skuId field cannot be null")
	private Long skuId;
	
	@NotNull (message = "quantity field cannot be null")
	private Integer quantity;
}
