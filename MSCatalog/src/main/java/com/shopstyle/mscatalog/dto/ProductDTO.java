package com.shopstyle.mscatalog.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.shopstyle.mscatalog.entities.Product;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDTO {

	private Long id;
	private String name;
	private String description;
	private String brand;
	private String material;
	private boolean active;
	private List<SkuDTO> skus;
	
	public ProductDTO(Product product) {
		this.id = product.getId();
		this.name = product.getName();
		this.description = product.getDescription();
		this.brand = product.getBrand();
		this.material = product.getMaterial();
		this.active = product.isActive();
		this.skus = product.getSkus().stream().map(SkuDTO::new).collect(Collectors.toList());
	}
}
