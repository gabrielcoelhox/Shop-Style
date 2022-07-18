package com.shopstyle.mscatalog.services;

import java.util.List;

import javax.validation.Valid;

import com.shopstyle.mscatalog.dto.SkuDTO;
import com.shopstyle.mscatalog.dto.SkuFormDTO;

public interface SkuService {

	List<SkuDTO> findAll();
	
	SkuDTO findById(Long id);

	SkuDTO insert(@Valid SkuFormDTO skuForm);

	SkuDTO update(Long id, @Valid SkuFormDTO skuForm);
	
	SkuDTO updateOrderSku(Long id, Integer quantity);

	void deleteById(Long id);
}
