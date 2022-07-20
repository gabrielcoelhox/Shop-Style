package com.shopstyle.mscatalog.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.shopstyle.mscatalog.dto.CategoryDTO;
import com.shopstyle.mscatalog.dto.CategoryFormDTO;
import com.shopstyle.mscatalog.dto.ProductDTO;
import com.shopstyle.mscatalog.entities.Category;
import com.shopstyle.mscatalog.exceptions.DefaultException;
import com.shopstyle.mscatalog.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

	private final CategoryRepository categoryRepository;

	public CategoryDTO insert(@Valid CategoryFormDTO form) {
		Category parentCategory = categoryRepository.findById(form.getParentId()).orElseThrow(
				() -> new DefaultException("Category with ID: " + form.getParentId() + " not found. Enter a valid ID.", "NOT_FOUND", 404));
		Category saveCategory = categoryRepository.save(new Category(form, parentCategory));
		if(parentCategory != null) {
			parentCategory.addChildren(saveCategory);
		}
		return new CategoryDTO(saveCategory);
	}

	public List<CategoryDTO> findAll() {
		return categoryRepository.findAll().stream().filter(c -> c.getParent() == null).map(CategoryDTO::new).collect(Collectors.toList());
	}

	public List<ProductDTO> findById(Long id) {
		Category category = categoryRepository.findById(id).orElseThrow(
				() -> new DefaultException("Category with ID: " + id + " not found. Enter a valid ID.", "NOT_FOUND", 404));	
		return category.getProducts().stream().map(ProductDTO::new).collect(Collectors.toList());
	}

	public CategoryDTO update(Long id, @Valid CategoryFormDTO form) {
		Category category = categoryRepository.findById(id).orElseThrow(
				() -> new DefaultException("Category with ID: " + id + " not found. Enter a valid ID.", "NOT_FOUND", 404));
		category.setName(form.getName());
		category.setActive(form.isActive());
		return new CategoryDTO(categoryRepository.save(category));
	}

	public void deleteById(Long id) {
		categoryRepository.findById(id).orElseThrow(
				() -> new DefaultException("Category with ID: " + id + " not found. Enter a valid ID.", "NOT_FOUND", 404));
		categoryRepository.deleteById(id);	
	}
}
