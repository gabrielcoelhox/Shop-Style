package com.shopstyle.msbffshop.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import com.shopstyle.msbffshop.clients.payment.dto.PaymentDTO;

@Component
@FeignClient("payment")
public interface PaymentClient {

	@GetMapping("/v1/payments")
	List<PaymentDTO> findAllPayments();
}
