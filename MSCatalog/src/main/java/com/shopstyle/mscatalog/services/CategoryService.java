package com.shopstyle.mscatalog.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopstyle.mscatalog.dto.CategoryDTO;
import com.shopstyle.mscatalog.dto.CategoryFormDTO;
import com.shopstyle.mscatalog.dto.ProductDTO;
import com.shopstyle.mscatalog.entities.Category;
import com.shopstyle.mscatalog.exceptions.MethodArgumentNotValidException;
import com.shopstyle.mscatalog.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	public CategoryDTO save(@Valid CategoryFormDTO categoryFormDto) {
		Category category = new Category();
		Category parentCategory = categoryRepository.findById(categoryFormDto.getParentId()).orElse(null);
		category.setName(categoryFormDto.getName());
		category.setActive(categoryFormDto.isActive());
		categoryRepository.save(category);
		if(parentCategory != null) {
			category.setParent(parentCategory);
			parentCategory.addChildren(category);
		}
		return new CategoryDTO(category);
	}

	public List<CategoryDTO> findAll() {
		return categoryRepository.findAll().stream().filter(c -> c.getParent() == null).map(CategoryDTO::new).collect(Collectors.toList());
	}

	public List<ProductDTO> findListProductsById(Long id) {
		Category category = categoryRepository.findById(id).orElseThrow(
				() -> new MethodArgumentNotValidException("Category with ID: " + id + " not found. Enter a valid ID."));	
		return category.getProducts().stream().map(ProductDTO::new).collect(Collectors.toList());
	}

	public CategoryDTO update(Long id, @Valid CategoryFormDTO categoryFormDto) {
		Category category = categoryRepository.findById(id).orElseThrow(
				() -> new MethodArgumentNotValidException("Category with ID: " + id + " not found. Enter a valid ID."));
		category.setName(categoryFormDto.getName());
		category.setActive(categoryFormDto.isActive());
		return new CategoryDTO(categoryRepository.save(category));
	}

	public void deleteById(Long id) {
		categoryRepository.findById(id).orElseThrow(
				() -> new MethodArgumentNotValidException("Category with ID: " + id + " not found. Enter a valid ID."));
		categoryRepository.deleteById(id);	
	}
}
