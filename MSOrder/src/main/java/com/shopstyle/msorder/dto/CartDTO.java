package com.shopstyle.msorder.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@NotNull
	private Long skuId;
	
	@NotNull
	private Integer quantity;
}
