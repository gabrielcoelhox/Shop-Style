package com.shopstyle.mscatalog.controllers;

import java.util.List;

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

import com.shopstyle.mscatalog.dto.ProductDTO;
import com.shopstyle.mscatalog.dto.ProductFormDTO;
import com.shopstyle.mscatalog.services.ProductService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/products")
@RequiredArgsConstructor
public class ProductController {

	private final ProductService productService;
	
	@GetMapping
	@ApiOperation(value = "Find all products")
	public ResponseEntity<List<ProductDTO>> findAll() {
		return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	@ApiOperation(value = "Find a product by id")
	public ResponseEntity<ProductDTO> findById(@PathVariable Long id) {
		return new ResponseEntity<>(productService.findById(id), HttpStatus.OK);
	}
	
	@PostMapping
	@Transactional
	@ApiOperation(value = "Insert a new product")
	public ResponseEntity<ProductDTO> insert(@RequestBody ProductFormDTO product) {
		return new ResponseEntity<>(productService.insert(product), HttpStatus.CREATED);
	}
	
	@ApiResponses({
	      @ApiResponse(code = 200, message = "Update done successfully", response = ProductDTO.class),
	      @ApiResponse(code = 403, message = "Profile not authorized to perform this operation", response = ProductDTO.class),
	      @ApiResponse(code = 404, message = "Product not found", response = ProductDTO.class)
	})
	@PutMapping("/{id}")
	@Transactional
	@ApiOperation(value = "Update a product")
	public ResponseEntity<ProductDTO> update(@PathVariable Long id, @RequestBody ProductFormDTO product) {
		return new ResponseEntity<>(productService.update(id, product), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value = "Delete a product")
	public ResponseEntity<Void> deleteById(@PathVariable Long id) {
		productService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
