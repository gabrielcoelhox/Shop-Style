package com.shopstyle.mscatalog.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopstyle.mscatalog.dto.CategoryDTO;
import com.shopstyle.mscatalog.dto.CategoryFormDTO;
import com.shopstyle.mscatalog.entities.Category;
import com.shopstyle.mscatalog.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private SequenceGeneratorService seqService;

	public CategoryDTO save(CategoryFormDTO categoryFormDTO) {
		Category category = new Category();
		category.setId(seqService.getSequenceNumber(Category.SEQUENCE_NAME));
		category.setName(categoryFormDTO.getName());
		category.setActive(categoryFormDTO.isActive());
		return new CategoryDTO(categoryRepository.save(category));
	}

	public List<CategoryDTO> findAll() {
		return categoryRepository.findAll().stream().map(CategoryDTO::new).collect(Collectors.toList());
	}
}
