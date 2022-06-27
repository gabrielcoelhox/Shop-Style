package com.shopstyle.mscatalog.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryFormDTO {

	@NotNull (message = "name field cannot be null") 
	@NotEmpty
	private String name;
	
	@NotNull
	private boolean active;
	
	private Long parentId;
}
