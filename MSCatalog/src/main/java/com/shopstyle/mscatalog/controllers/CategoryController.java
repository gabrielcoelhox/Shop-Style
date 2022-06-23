package com.shopstyle.mscatalog.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopstyle.mscatalog.dto.CategoryDTO;
import com.shopstyle.mscatalog.dto.CategoryFormDTO;
import com.shopstyle.mscatalog.dto.ProductDTO;
import com.shopstyle.mscatalog.services.CategoryService;

@RestController
@RequestMapping(value = "/v1/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@GetMapping
	public ResponseEntity<List<CategoryDTO>> findAll(){
		return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}/products")
	public ResponseEntity<List<ProductDTO>> findListProductsById(@PathVariable Long id){
		return new ResponseEntity<>(categoryService.findListProductsById(id), HttpStatus.OK);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<CategoryDTO> save(@RequestBody @Valid CategoryFormDTO categoryFormDto){
		return new ResponseEntity<>(categoryService.save(categoryFormDto), HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<CategoryDTO> update(@PathVariable Long id, @RequestBody @Valid CategoryFormDTO categoryFormDto){
		return new ResponseEntity<>(categoryService.update(id, categoryFormDto), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id){
		categoryService.deleteById(id); 
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
