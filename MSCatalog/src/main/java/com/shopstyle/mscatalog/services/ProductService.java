package com.shopstyle.mscatalog.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopstyle.mscatalog.dto.ProductDTO;
import com.shopstyle.mscatalog.dto.ProductFormDTO;
import com.shopstyle.mscatalog.entities.Category;
import com.shopstyle.mscatalog.entities.Product;
import com.shopstyle.mscatalog.exceptions.ObjectNotFoundException;
import com.shopstyle.mscatalog.repository.CategoryRepository;
import com.shopstyle.mscatalog.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private SequenceGeneratorService seqService;
	
	public List<ProductDTO> findAll() {
		return productRepository.findAll().stream().map(ProductDTO::new).collect(Collectors.toList());
	}
	
	public ProductDTO save(ProductFormDTO productFormDto) {
		Product product = new Product();
		product.setId(seqService.getSequenceNumber(Product.SEQUENCE_NAME));
		product.setName(productFormDto.getName());
		product.setDescription(productFormDto.getDescription());
		product.setActive(productFormDto.isActive());
		for(Long idCategory : productFormDto.getCategory_ids()) {
			Category findCategory = categoryRepository.findById(idCategory).orElseThrow(
					() -> new ObjectNotFoundException("Category ID: " + idCategory + " not found."));
			if(findCategory.isActive()) {
				product.addCategory(findCategory);
			}
		}
		return new ProductDTO(productRepository.save(product));
	}

	public ProductDTO findById(Long id) {
		return new ProductDTO( 
				productRepository.findById(id).orElseThrow(
				() -> new ObjectNotFoundException("Product ID: " + id + " not found."))
			);
	}

	public void deleteById(Long id) {
		findById(id);
		productRepository.deleteById(id);
	}

	public ProductDTO update(Long id, ProductFormDTO productFormDto) {
		Product product = productRepository.findById(id).orElseThrow(
				() -> new ObjectNotFoundException("Product ID: " + id + " not found."));
		product.setName(productFormDto.getName());
		product.setDescription(productFormDto.getDescription());
		product.setActive(productFormDto.isActive());
		product.getCategories().clear();
		for(Long idCategory : productFormDto.getCategory_ids()) {
			Category findCategory = categoryRepository.findById(idCategory).orElseThrow(
					() -> new ObjectNotFoundException("Category ID: " + idCategory + " not found."));
			if(findCategory.isActive()) {
				product.addCategory(findCategory);
			}
		}
		return new ProductDTO(productRepository.save(product));
	}
}
