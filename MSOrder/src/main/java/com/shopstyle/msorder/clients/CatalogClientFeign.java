package com.shopstyle.msorder.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shopstyle.msorder.clients.dto.Sku;

@Component
@FeignClient("catalog")
public interface CatalogClientFeign {

	@RequestMapping("/v1/skus/{id}")
	Sku getSku(@RequestParam Long id);
}
