package com.shopstyle.mscatalog.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class ProductFormDTO {

	private String name;
	private String description;
	private boolean active;
	private List<Long> category_ids;
}
