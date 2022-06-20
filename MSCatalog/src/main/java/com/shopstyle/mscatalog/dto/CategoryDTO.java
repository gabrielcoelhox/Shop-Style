package com.shopstyle.mscatalog.dto;

import com.shopstyle.mscatalog.entities.Category;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDTO {
	
	private String name;
	private boolean active;

	public CategoryDTO(Category category) {
		this.name = category.getName();
		this.active = category.isActive();
	}
}
