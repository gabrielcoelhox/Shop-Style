package com.shopstyle.mscatalog.controllers;

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

import com.shopstyle.mscatalog.dto.SkuDTO;
import com.shopstyle.mscatalog.dto.SkuFormDTO;
import com.shopstyle.mscatalog.services.SkuServiceImpl;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/v1/skus")
public class SkuController {

	@Autowired
	private SkuServiceImpl skuServiceImpl;
	
	@GetMapping("/{id}")
	@Transactional
	@ApiOperation(value = "Find a sku by id")
	public ResponseEntity<SkuDTO> findById(@PathVariable Long id){
		return new ResponseEntity<>(skuServiceImpl.findById(id), HttpStatus.OK);
	}
	
	@PostMapping
	@Transactional
	@ApiOperation(value = "Insert a new sku")
	public ResponseEntity<SkuDTO> insert(@RequestBody @Valid SkuFormDTO skuForm){
		return new ResponseEntity<>(skuServiceImpl.insert(skuForm), HttpStatus.CREATED);
	}
	
	@ApiResponses({
	      @ApiResponse(code = 200, message = "Update done successfully", response = SkuDTO.class),
	      @ApiResponse(code = 403, message = "Profile not authorized to perform this operation", response = SkuDTO.class),
	      @ApiResponse(code = 404, message = "Sku not found", response = SkuDTO.class)
	})
	@PutMapping("/{id}")
	@Transactional
	@ApiOperation(value = "Update a sku")
	public ResponseEntity<SkuDTO> update(@PathVariable Long id, @RequestBody @Valid SkuFormDTO skuForm){
		return new ResponseEntity<>(skuServiceImpl.update(id, skuForm), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	@ApiOperation(value = "Delete a sku")
	public ResponseEntity<Void> deleteById(@PathVariable Long id){
		skuServiceImpl.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
