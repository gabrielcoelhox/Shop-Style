package com.shopstyle.msorder.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.shopstyle.msorder.clients.CatalogClientFeign;
import com.shopstyle.msorder.clients.CustomerClientFeign;
import com.shopstyle.msorder.clients.PaymentClientFeign;
import com.shopstyle.msorder.clients.dto.Address;
import com.shopstyle.msorder.clients.dto.Customer;
import com.shopstyle.msorder.clients.dto.Installment;
import com.shopstyle.msorder.clients.dto.Payment;
import com.shopstyle.msorder.clients.dto.Sku;
import com.shopstyle.msorder.dto.CartDTO;
import com.shopstyle.msorder.dto.OrderDTO;
import com.shopstyle.msorder.dto.OrderFormDTO;
import com.shopstyle.msorder.entities.Order;
import com.shopstyle.msorder.enums.Status;
import com.shopstyle.msorder.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

	private final CatalogClientFeign catalogClient;

	private final PaymentClientFeign paymentClient;

	private final CustomerClientFeign customerClient;

	private final OrderRepository orderRepository;
	
	private final RabbitTemplate rabbitTemplate;
	
	@Value("${mq.queues.sku-order}")
	private String queueSkuOrder;
	
	@Value("${mq.queues.payment-order}")
	private String queuePaymentOrder;
	
	public List<OrderDTO> findAll() {
		return orderRepository.findAll().stream().map(OrderDTO::new).collect(Collectors.toList());
	}
	
	public List<OrderDTO> findByCustomerId(Long id, LocalDate startDate, LocalDate endDate, Status status) {

		Stream<Order> orderStream = orderRepository.findByCustomerId(id).stream();
		if (status != null) {
			orderStream = orderStream.filter(o -> (o.getStatus() == status));
		}
		if (startDate != null) {
			orderStream = orderStream.filter(o -> (o.getDate().isAfter(startDate) || o.getDate().isEqual(startDate)));
		}
		if (endDate != null) {
			orderStream = orderStream.filter(o -> (o.getDate().isBefore(endDate) || o.getDate().isEqual(endDate)));
		}
		return orderStream.map(OrderDTO::new).collect(Collectors.toList());
	}
	
	public OrderDTO update(OrderFormDTO orderForm) {
		
		Order order = new Order();
		Customer customer = customerClient.getCustomer(orderForm.getCustomer().getId());
		Address address = customerClient.getAddress(orderForm.getCustomer().getAddressId());
		Payment payment = paymentClient.getPayment(orderForm.getPayment().getId());
		Installment installment = new Installment();
		installment.setAmount(orderForm.getPayment().getInstallments());
		installment.setPayment(payment);
		Double total = 0.0;
		List<Sku> cart = new ArrayList<>();
		
		for(CartDTO cartDto : orderForm.getCart()) {
			Sku sku = catalogClient.getSku(cartDto.getSkuId());
			sku.setQuantity(cartDto.getQuantity());
			cart.add(sku);
			total += (sku.getPrice() * cartDto.getQuantity());
		}
	
		order.setCustomer(customer);
		order.setAddress(address);
		order.setPayment(payment);
		order.setInstallment(installment);
		order.setCart(cart);
		order.setDate(LocalDate.now());
		order.setStatus(Status.PROCESSING_PAYMENT);
		order.setTotal(total);
		return new OrderDTO(orderRepository.save(order));
	}
}
