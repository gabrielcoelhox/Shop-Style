package com.shopstyle.msbffshop.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.shopstyle.msbffshop.clients.catalog.dto.CategoryDTO;
import com.shopstyle.msbffshop.clients.catalog.dto.ProductDTO;

@Component
@FeignClient("catalog")
public interface CatalogClient {

	@GetMapping("/v1/products")
	List<ProductDTO> findAllProducts();
	
	@GetMapping("/v1/products/{id}")
	ProductDTO findProductById(@PathVariable Long id);
	
	@GetMapping("/v1/categories")
	List<CategoryDTO> findAllCategories();
	
	@GetMapping("/v1/categories/{id}/products")
	List<ProductDTO> findProductsByIdCategory(@PathVariable Long id);

}
