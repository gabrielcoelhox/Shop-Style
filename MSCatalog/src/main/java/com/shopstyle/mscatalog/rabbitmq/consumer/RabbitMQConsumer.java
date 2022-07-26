package com.shopstyle.mscatalog.rabbitmq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.shopstyle.mscatalog.rabbitmq.entities.Sku;
import com.shopstyle.mscatalog.rabbitmq.entities.SkuOrder;
import com.shopstyle.mscatalog.services.SkuService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RabbitMQConsumer {

	private final SkuService service;
	
	@Bean
	public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	@RabbitListener(queues = "${mq.queues.order-sku}")
	private void processMessage(SkuOrder skuOrder) {			
		for(Sku sku : skuOrder.getSkus()) {
			service.updateOrderSku(sku.getId(), sku.getQuantity());
		}
	}
}
