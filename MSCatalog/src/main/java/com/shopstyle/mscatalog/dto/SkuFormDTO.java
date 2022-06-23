package com.shopstyle.mscatalog.dto;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SkuFormDTO {

	@NotNull
	private Double price;
	
	@NotNull
	private Integer quantity;
	
	@NotNull @NotEmpty
	private String color;
	
	@NotNull @NotEmpty
	private String size;
	
	@NotNull
	private Integer height;
	
	@NotNull
	private Integer width;
	
	@NotNull
	private Long productId;
	
	@NotNull @NotEmpty
	private List<String> images;
}
