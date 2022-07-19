package com.shopstyle.mspayment.rabbitmq.publisher;

import javax.annotation.PostConstruct;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class RabbitMQPublisher {

	@Value("${mq.queues.order-payment}")
	private String queueOrderPayment;
	
	@Value("${mq.exchange.order}")
	private String exchange;
	
	@Autowired
	private AmqpAdmin amqpAdmin;
	
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
		Queue paymentOrderQueue = this.queue(queueOrderPayment);
		
		DirectExchange exchange = this.directExchange();
		
		Binding relatePaymentOrder = this.relate(paymentOrderQueue, exchange);
		
		this.amqpAdmin.declareQueue(paymentOrderQueue);
		
		this.amqpAdmin.declareExchange(exchange);
		
		this.amqpAdmin.declareBinding(relatePaymentOrder);
	}
}
