package com.shopstyle.mscatalog.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VariationFormDTO {

	private String color;
	private String size;
	private Double price;
	private Integer quantity;
	private Long product_id;
}
