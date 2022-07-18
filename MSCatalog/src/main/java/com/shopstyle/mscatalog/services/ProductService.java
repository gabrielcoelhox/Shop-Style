package com.shopstyle.mscatalog.services;

import java.util.List;

import javax.validation.Valid;

import com.shopstyle.mscatalog.dto.ProductDTO;
import com.shopstyle.mscatalog.dto.ProductFormDTO;

public interface ProductService {

	List<ProductDTO> findAll();
	
	ProductDTO findById(Long id);
	
	ProductDTO insert(@Valid ProductFormDTO productForm);
	
	ProductDTO update(Long id, @Valid ProductFormDTO productForm);

	void deleteById(Long id);
}
