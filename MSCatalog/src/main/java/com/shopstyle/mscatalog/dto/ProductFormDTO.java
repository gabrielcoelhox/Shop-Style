package com.shopstyle.mscatalog.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductFormDTO {

	@NotNull @NotEmpty
	private String name;
	
	@NotNull @NotEmpty
	private String description;
	
	@NotNull @NotEmpty
	private String brand;
	
	private String material;
	
	@NotNull
	private boolean active;
	
	@NotNull
	private Long categoryId;
}
