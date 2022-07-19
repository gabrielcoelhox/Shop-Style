package com.shopstyle.msorder.rabbitmq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.shopstyle.msorder.services.OrderService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RabbitMQConsumer {

	private final OrderService orderService;
	
	@Bean
	public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
		return new Jackson2JsonMessageConverter();
	}
	
	@RabbitListener(queues = "${mq.queues.order-payment}")
	private void processMessage(PaymentOrderStatus paymentOrderStatus) {			
		orderService.updateStatusPayment(paymentOrderStatus);
	}
}
