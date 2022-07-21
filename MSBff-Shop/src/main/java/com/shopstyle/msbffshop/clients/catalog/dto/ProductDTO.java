package com.shopstyle.msbffshop.clients.catalog.dto;

import java.util.List;

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
}
