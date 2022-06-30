package com.shopstyle.msorder.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shopstyle.msorder.clients.dto.Payment;

@Component
@FeignClient("payment")
public interface PaymentClientFeign {

	@RequestMapping("/v1/payments/{id}")
	Payment getPayment(@RequestParam Long id);
}
