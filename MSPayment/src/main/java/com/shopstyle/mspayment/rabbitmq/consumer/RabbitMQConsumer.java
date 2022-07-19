package com.shopstyle.mspayment.rabbitmq.consumer;

import java.util.Optional;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.shopstyle.mspayment.entities.Payment;
import com.shopstyle.mspayment.rabbitmq.publisher.PaymentOrderStatus;
import com.shopstyle.mspayment.rabbitmq.publisher.Status;
import com.shopstyle.mspayment.repository.PaymentRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RabbitMQConsumer {

	@Bean
	public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
		return new Jackson2JsonMessageConverter();
	}
	
	@Value("${mq.queues.order-payment}")
	private String queueOrderPayment;
	
	private final PaymentRepository paymentRepository;
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@RabbitListener(queues = "${mq.queues.payment-order}")
	private void processMessage(PaymentOrder paymentOrder) {
		Optional<Payment> optPayment = paymentRepository.findById(paymentOrder.getPayment().getId());
		Status status = Status.PROCESSING_PAYMENT;
		if(optPayment.isPresent()) {
			if(optPayment.get().isActive()) {
				if (paymentOrder.getPayment().getInstallments() > 0 && paymentOrder.getPayment().getInstallments() <= 12) {
					if (optPayment.get().isInstallments()){
						status = Status.PAYMENT_SUCCESSFUL;
					} else {
						status = Status.PAYMENT_NOT_INSTALLMENT;
					}
				} else if (paymentOrder.getPayment().getInstallments() == 0){
					if (!optPayment.get().isInstallments()){
						status = Status.PAYMENT_SUCCESSFUL;
					}
				} else {
					status = Status.PAYMENT_AMOUNT_NOT_AVAILABLE;
				}	
			} else {
				status = Status.PAYMENT_INACTIVE;
			}
		} else {
			status = Status.PAYMENT_NOT_FOUND;
		}
		rabbitTemplate.convertAndSend(queueOrderPayment, new PaymentOrderStatus(paymentOrder.getOrderId(), status));
	}
}
