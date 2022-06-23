package com.shopstyle.mscatalog.dto;

import java.util.List;

import com.shopstyle.mscatalog.entities.Category;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDTO {
	
	private Long id;
	private String name;
	private boolean active;
	private List<Category> children;
	
	public CategoryDTO(Category category) {
		this.id = category.getId();
		this.name = category.getName();
		this.active = category.isActive();
		this.children = category.getChildren();
	}
}
