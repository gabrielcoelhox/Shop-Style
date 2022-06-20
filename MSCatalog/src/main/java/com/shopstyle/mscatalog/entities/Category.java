package com.shopstyle.mscatalog.entities;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Document
@Getter
@Setter
public class Category {

	@Transient
	public static final String SEQUENCE_NAME="category_sequence";

	@Id
	private Long id;
	
	private String name;
	private boolean active;
	private List<Product> products = new ArrayList<>();
}
