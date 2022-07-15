package com.shopstyle.msorder.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long skuId;
	private Integer quantity;
}
