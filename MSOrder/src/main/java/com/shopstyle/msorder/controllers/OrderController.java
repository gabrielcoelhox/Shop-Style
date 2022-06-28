package com.shopstyle.msorder.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopstyle.msorder.services.OrderService;

@RestController
@RequestMapping("/v1/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;
}
