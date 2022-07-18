package com.shopstyle.msorder.rabbitmq.publisher;

import javax.annotation.PostConstruct;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class RabbitMQPublisher {

	@Value("${mq.queues.sku-order}")
	private String queueSkuOrder;
	
	@Value("${mq.queues.payment-order}")
	private String queuePaymentOrder;
	
	@Value("${mq.exchange.order}")
	private String exchange;
	
	private final AmqpAdmin amqpAdmin;
	
	private Queue queue(String queueName) {
		return new Queue(queueName, true, false, false);
	}
	
	private DirectExchange directExchange() {
		return new DirectExchange(exchange);
	}
	
	private Binding relate(Queue queue, DirectExchange exchange) {
		return new Binding(queue.getName(),Binding.DestinationType.QUEUE, exchange.getName(), queue.getName(), null);
	}
	
	@PostConstruct
	private void add() {
		Queue skuOrderQueue = this.queue(queueSkuOrder);
		Queue paymentOrderQueue = this.queue(queuePaymentOrder);
		
		DirectExchange exchange = this.directExchange();
		
		Binding relateSkuOrder = this.relate(skuOrderQueue, exchange);
		Binding relatePaymentOrder = this.relate(paymentOrderQueue, exchange);
		
		this.amqpAdmin.declareQueue(skuOrderQueue);
		this.amqpAdmin.declareQueue(paymentOrderQueue);
		
		this.amqpAdmin.declareExchange(exchange);
		
		this.amqpAdmin.declareBinding(relateSkuOrder);
		this.amqpAdmin.declareBinding(relatePaymentOrder);
	}
}
