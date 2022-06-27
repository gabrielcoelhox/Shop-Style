package com.shopstyle.mscatalog.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopstyle.mscatalog.dto.ProductDTO;
import com.shopstyle.mscatalog.dto.ProductFormDTO;
import com.shopstyle.mscatalog.entities.Category;
import com.shopstyle.mscatalog.entities.Product;
import com.shopstyle.mscatalog.exceptions.MethodArgumentNotValidException;
import com.shopstyle.mscatalog.repository.CategoryRepository;
import com.shopstyle.mscatalog.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<ProductDTO> findAll() {
		return productRepository.findAll().stream().map(ProductDTO::new).collect(Collectors.toList());
	}
	
	public ProductDTO findById(Long id) {
		return new ProductDTO(productRepository.findById(id).orElseThrow(
				() -> new MethodArgumentNotValidException("Product with ID: " + id + " not found. Enter a valid ID.")));
	}
	
	public ProductDTO save(@Valid ProductFormDTO productFormDto) {
		
		Category category = categoryRepository.findById(productFormDto.getCategoryId()).orElseThrow(
				() -> new MethodArgumentNotValidException("Category with ID: " + productFormDto.getCategoryId() + " not found. Enter a valid ID."));
		Product product = new Product();
		product.setName(productFormDto.getName());
		product.setDescription(productFormDto.getDescription());
		product.setActive(productFormDto.isActive());
		product.setBrand(productFormDto.getBrand());
		product.setMaterial(productFormDto.getMaterial());

		if(category.isActive() && category.getChildren().isEmpty()) {
			product.setCategory(category);
			return new ProductDTO(productRepository.save(product));
		} else {
			throw new MethodArgumentNotValidException("Not is possible to add a product to this category.");
		}
	}
	
	public ProductDTO update(Long id, @Valid ProductFormDTO productFormDto) {
		Product product = productRepository.findById(id).orElseThrow(
				() -> new MethodArgumentNotValidException("Product ID: " + id + " not found."));
		Category category = categoryRepository.findById(productFormDto.getCategoryId()).orElseThrow(
				() -> new MethodArgumentNotValidException("Category with ID: " + productFormDto.getCategoryId() + " not found. Enter a valid ID."));
		product.setName(productFormDto.getName());
		product.setDescription(productFormDto.getDescription());
		product.setActive(productFormDto.isActive());
		product.setBrand(productFormDto.getBrand());
		product.setMaterial(productFormDto.getMaterial());
		
		if(category.isActive() && category.getChildren().isEmpty()) {
			product.setCategory(category);
			return new ProductDTO(productRepository.save(product));
		} else {
			throw new MethodArgumentNotValidException("Not is possible to add a product to this category.");
		}
	}

	public void deleteById(Long id) {
		findById(id);
		productRepository.deleteById(id);
	}
}
