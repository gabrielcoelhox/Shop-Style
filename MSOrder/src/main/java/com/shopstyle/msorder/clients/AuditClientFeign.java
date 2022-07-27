package com.shopstyle.msorder.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.shopstyle.msorder.entities.Order;

@Component
@FeignClient("audit")
public interface AuditClientFeign {

	@PostMapping("/v1/audit")
	void saveOrderAudit(@RequestBody Order order);
}