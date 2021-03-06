package com.shopstyle.mscatalog.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Media {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull @NotEmpty
	private String imageUrl;
	
	@ManyToOne
	@JsonIgnore
	private Sku sku;
	
	public Media(@NotNull @NotEmpty String imageUrl, Sku sku) {
		this.imageUrl = imageUrl;
		this.sku = sku;
	}
}
