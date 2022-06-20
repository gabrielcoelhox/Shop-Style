package com.shopstyle.mscatalog.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.shopstyle.mscatalog.dto.ProductFormDTO;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Document
@Getter
@Setter
@NoArgsConstructor
public class Product implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Transient
	public static final String SEQUENCE_NAME = "product_sequence";

	@Id
	private Long id;
	
	private String name;
	private String description;
	private boolean active;
	
	@DBRef
	private List<Category> categories = new ArrayList<>();
	
	private List<Variation> variations = new ArrayList<>();
	
	public Product(ProductFormDTO product) {
		this.name = product.getName();
		this.description = product.getDescription();
		this.active = product.isActive();
	}
	
	public List<Category> getCategories() {
		return categories;
	}
	
	public void addCategory(Category category) {
		this.categories.add(category);
	}

	public List<Variation> getVariations() {
		return variations;
	}
	
	public void addVariation(Variation variation) {
		this.variations.add(variation);
	}
}
