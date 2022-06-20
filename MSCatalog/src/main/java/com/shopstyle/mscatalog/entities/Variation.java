package com.shopstyle.mscatalog.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Document
@Getter
@Setter
public class Variation {

	@Transient
	public static final String SEQUENCE_NAME = "variation_sequence";
	
	@Id
	private Long id;
	
	private String color;
	private String size;
	private Double price;
	private Integer quantity;
	
	@DBRef
	private Product product;
}
