package com.shopstyle.mscatalog.dto;

import java.util.List;

import com.shopstyle.mscatalog.entities.Product;
import com.shopstyle.mscatalog.entities.Sku;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductDTO {

	private Long id;
	private String name;
	private String description;
	private String brand;
	private String material;
	private Boolean active;
	private List<Sku> skus;
	
	public ProductDTO(Product product) {
		this.id = product.getId();
		this.name = product.getName();
		this.description = product.getDescription();
		this.brand = product.getBrand();
		this.material = product.getMaterial();
		this.active = product.isActive();
		this.skus = product.getSkus();
	}
}
