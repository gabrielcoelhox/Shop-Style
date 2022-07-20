package com.shopstyle.mscatalog.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.shopstyle.mscatalog.dto.ProductDTO;
import com.shopstyle.mscatalog.dto.ProductFormDTO;
import com.shopstyle.mscatalog.entities.Category;
import com.shopstyle.mscatalog.entities.Product;
import com.shopstyle.mscatalog.exceptions.CategoryNotValidException;
import com.shopstyle.mscatalog.exceptions.DefaultException;
import com.shopstyle.mscatalog.repository.CategoryRepository;
import com.shopstyle.mscatalog.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;
	
	private final CategoryRepository categoryRepository;
	
	public List<ProductDTO> findAll() {
		return productRepository.findAll().stream().map(ProductDTO::new).collect(Collectors.toList());
	}
	
	public ProductDTO findById(Long id) {
		return new ProductDTO(productRepository.findById(id).orElseThrow(
				() -> new DefaultException("Product with ID: " + id + " not found. Enter a valid ID.", "NOT_FOUND", 404)));
	}
	
	public ProductDTO insert(@Valid ProductFormDTO form) {
		Category category = categoryRepository.findById(form.getCategoryId()).orElseThrow(
				() -> new DefaultException("Category with ID: " + form.getCategoryId() + " not found. Enter a valid ID.", "NOT_FOUND", 404));

		if(category.isActive() && category.getChildren().isEmpty()) {
			return new ProductDTO(productRepository.save(new Product(form, category)));
		} else {
			throw new CategoryNotValidException("Not is possible to add a product to this category.");
		}
	}
	
	public ProductDTO update(Long id, @Valid ProductFormDTO form) {
		Product product = productRepository.findById(id).orElseThrow(
				() -> new DefaultException("Product with ID: " + id + " not found.", "NOT_FOUND", 404));
		Category category = categoryRepository.findById(form.getCategoryId()).orElseThrow(
				() -> new DefaultException("Category with ID: " + form.getCategoryId() + " not found. Enter a valid ID.", "NOT_FOUND", 404));
		
		product.setName(form.getName());
		product.setDescription(form.getDescription());
		product.setActive(form.isActive());
		product.setBrand(form.getBrand());
		product.setMaterial(form.getMaterial());
		
		if(category.isActive() && category.getChildren().isEmpty()) {
			product.setCategory(category);
			return new ProductDTO(productRepository.save(product));
		} else {
			throw new CategoryNotValidException("Not is possible to add a product to this category.");
		}
	}
	
	public void deleteById(Long id) {
		productRepository.findById(id).orElseThrow(
				() -> new DefaultException("Address with id: " + id + " not found. Enter a valid ID.", "NOT_FOUND", 404));
		productRepository.deleteById(id);
	}
}
