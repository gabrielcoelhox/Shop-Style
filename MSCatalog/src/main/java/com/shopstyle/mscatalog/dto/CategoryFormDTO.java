package com.shopstyle.mscatalog.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryFormDTO {

	@NotNull (message = "name field cannot be null") 
	@NotEmpty
	private String name;
	
	@NotNull
	private boolean active;
	
	private Long parentId;
}
