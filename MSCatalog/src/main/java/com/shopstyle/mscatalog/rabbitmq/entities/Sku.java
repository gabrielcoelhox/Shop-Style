package com.shopstyle.mscatalog.rabbitmq.entities;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Sku implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private Double price;
	private Integer quantity;
	private String color;
	private String size;
	private Integer height;
	private Integer width;
}
