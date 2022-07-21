package com.shopstyle.msbffshop.clients.catalog.dto;

import java.util.List;

import com.shopstyle.msbffshop.clients.entities.Media;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SkuDTO {

	private Long id;
	private Double price;
	private Integer quantity;
	private String color;
	private String size;
	private Integer height;
	private Integer width;
	private List<Media> images;
}
