package com.shopstyle.msbffshop.clients.catalog.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CategoryDTO {
	
	private Long id;
	private String name;
	private boolean active;
	private List<CategoryDTO> children = new ArrayList<>();
}