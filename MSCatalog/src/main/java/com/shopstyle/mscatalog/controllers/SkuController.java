package com.shopstyle.mscatalog.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopstyle.mscatalog.dto.SkuDTO;
import com.shopstyle.mscatalog.dto.SkuFormDTO;
import com.shopstyle.mscatalog.services.SkuService;

@RestController
@RequestMapping("/v1/skus")
public class SkuController {

	@Autowired
	private SkuService skuService;
	
	@PostMapping
	@Transactional
	public ResponseEntity<SkuDTO> save(@RequestBody @Valid SkuFormDTO skuForm){
		return new ResponseEntity<>(skuService.save(skuForm), HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<SkuDTO> update(@PathVariable Long id, @RequestBody @Valid SkuFormDTO skuForm){
		return new ResponseEntity<>(skuService.update(id, skuForm), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Void> deleteById(@PathVariable Long id){
		skuService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
