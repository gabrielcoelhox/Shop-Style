package com.shopstyle.mscatalog.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.shopstyle.mscatalog.entities.Product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductDTO {

	private String name;
	private String description;
	private boolean active;
	private List<CategoryDTO> categories;
	private List<VariationDTO> variations;
	
	public ProductDTO(Product product) {
		this.name = product.getName();
		this.description = product.getDescription();
		this.active = product.isActive();
		this.categories = product.getCategories().stream().map(CategoryDTO::new).collect(Collectors.toList());
		this.variations = product.getVariations().stream().map(VariationDTO::new).collect(Collectors.toList());
	}
}
