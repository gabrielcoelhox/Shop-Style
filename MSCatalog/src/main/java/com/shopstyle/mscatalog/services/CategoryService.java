package com.shopstyle.mscatalog.services;

import java.util.List;

import javax.validation.Valid;

import com.shopstyle.mscatalog.dto.CategoryDTO;
import com.shopstyle.mscatalog.dto.CategoryFormDTO;
import com.shopstyle.mscatalog.dto.ProductDTO;

public interface CategoryService {

	List<CategoryDTO> findAll();
	
	List<ProductDTO> findById(Long id);
	
	CategoryDTO insert(@Valid CategoryFormDTO categoryForm);
	
	CategoryDTO update (Long id,@Valid CategoryFormDTO categoryForm);
	
	void deleteById(Long id);
}
