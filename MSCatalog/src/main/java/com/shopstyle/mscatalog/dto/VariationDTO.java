package com.shopstyle.mscatalog.dto;

import com.shopstyle.mscatalog.entities.Variation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class VariationDTO {

	private String color;
	private String size;
	private Double price;
	private Integer quantity;
	
	public VariationDTO(Variation variation) {
		this.color = variation.getColor();
		this.size = variation.getSize();
		this.price = variation.getPrice();
		this.quantity = variation.getQuantity();
	}
}
