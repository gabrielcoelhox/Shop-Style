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
import com.shopstyle.msorder.dto.PaymentDTO;
import com.shopstyle.msorder.entities.Order;
import com.shopstyle.msorder.enums.Status;
import com.shopstyle.msorder.exceptions.DefaultException;
import com.shopstyle.msorder.exceptions.MethodArgumentNotValidException;
import com.shopstyle.msorder.rabbitmq.consumer.PaymentOrderStatus;
import com.shopstyle.msorder.rabbitmq.entities.PaymentOrder;
import com.shopstyle.msorder.rabbitmq.entities.SkuOrder;
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
	
	public OrderDTO update(OrderFormDTO form) {
		
		Order o = new Order();
		Customer customer = customerClient.getCustomer(form.getCustomer().getId());
		Address address = customerClient.getAddress(form.getCustomer().getAddressId());
		Payment payment = paymentClient.getPayment(form.getPayment().getId());
		Installment installment = new Installment();
		installment.setAmount(form.getPayment().getInstallments());
		installment.setPayment(payment);
		Double total = 0.0;
		List<Sku> cart = new ArrayList<>();
		
		for (CartDTO cartDto : form.getCart()) {
			Sku sku = catalogClient.getSku(cartDto.getSkuId());
			if(sku.getQuantity() >= cartDto.getQuantity()) {
				sku.setQuantity(cartDto.getQuantity());
			} else {
				throw new MethodArgumentNotValidException("Quantity unavailable Sku ID: " + sku.getId());
			}
			cart.add(sku);
			total += (sku.getPrice() * cartDto.getQuantity());
		}
	
		o.setCustomer(customer);
		o.setAddress(address);
		o.setPayment(payment);
		o.setInstallment(installment);
		o.setCart(cart);
		o.setDate(LocalDate.now());
		o.setStatus(Status.PROCESSING_PAYMENT);
		o.setTotal(total);
		
		orderRepository.save(o);
		rabbitTemplate.convertAndSend(queueSkuOrder, builderSkuOrder(o));
		rabbitTemplate.convertAndSend(queuePaymentOrder, builderPaymentOrder(o));
		return new OrderDTO(o);
	}
	
	public OrderDTO updateStatusPayment(PaymentOrderStatus orderStatus) {
		Order order = orderRepository.findById(orderStatus.getOrderId()).orElseThrow(
				() -> new DefaultException("Order with ID: " + orderStatus.getOrderId() + " not found. Enter a valid ID.", "NOT_FOUND", 404));
		order.setStatus(orderStatus.getStatus());
		return new OrderDTO(orderRepository.save(order));
	}
	
	private PaymentOrder builderPaymentOrder(Order o) {
		
		PaymentOrder paymentOrder = new PaymentOrder();
		PaymentDTO paymentDto = new PaymentDTO();
		paymentDto.setId(o.getPayment().getId());
		paymentDto.setInstallments(o.getInstallment().getAmount());
		paymentOrder.setOrderId(o.getId());
		paymentOrder.setPayment(paymentDto);
		return paymentOrder;
	}
	
	private SkuOrder builderSkuOrder(Order o) {
		SkuOrder skuOrder = new SkuOrder();
		skuOrder.setOrderId(o.getId());
		skuOrder.setSkus(o.getCart());
		return skuOrder;
	}
}
