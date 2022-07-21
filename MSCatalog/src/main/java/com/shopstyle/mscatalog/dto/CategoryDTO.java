package com.shopstyle.mscatalog.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.shopstyle.mscatalog.entities.Category;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CategoryDTO {
	
	private Long id;
	private String name;
	private boolean active;
	private List<CategoryDTO> children;
	
	public CategoryDTO(Category category) {
		this.id = category.getId();
		this.name = category.getName();
		this.active = category.isActive();
		this.children = category.getChildren().stream().map(CategoryDTO::new).collect(Collectors.toList());
	}
}
