package com.shopstyle.msorder.clients.dto;

import lombok.Data;

@Data
public class Sku {

	private Long id;
	private Double price;
	private Integer quantity;
	private String color;
	private String size;
	private Integer height;
	private Integer width;
}
