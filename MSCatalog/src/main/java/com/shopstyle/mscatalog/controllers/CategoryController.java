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

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/v1/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@GetMapping
	@ApiOperation(value = "Find all categories")
	public ResponseEntity<List<CategoryDTO>> findAll(){
		return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}/products")
	@ApiOperation(value = "Find a category by id")
	public ResponseEntity<List<ProductDTO>> findListProductsById(@PathVariable Long id){
		return new ResponseEntity<>(categoryService.findListProductsById(id), HttpStatus.OK);
	}
	
	@PostMapping
	@Transactional
	@ApiOperation(value = "Insert a new category")
	public ResponseEntity<CategoryDTO> insert(@RequestBody @Valid CategoryFormDTO categoryFormDto){
		return new ResponseEntity<>(categoryService.save(categoryFormDto), HttpStatus.CREATED);
	}
	
	@ApiResponses({
	      @ApiResponse(code = 200, message = "Update done successfully", response = CategoryDTO.class),
	      @ApiResponse(code = 403, message = "Profile not authorized to perform this operation", response = CategoryDTO.class),
	      @ApiResponse(code = 404, message = "Category not found", response = CategoryDTO.class)
	})
	@PutMapping("/{id}")
	@Transactional
	@ApiOperation(value = "Update a category")
	public ResponseEntity<CategoryDTO> update(@PathVariable Long id, @RequestBody @Valid CategoryFormDTO categoryFormDto){
		return new ResponseEntity<>(categoryService.update(id, categoryFormDto), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	@ApiOperation(value = "Delete a category")
	public ResponseEntity<Void> deleteById(@PathVariable Long id){
		categoryService.deleteById(id); 
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
