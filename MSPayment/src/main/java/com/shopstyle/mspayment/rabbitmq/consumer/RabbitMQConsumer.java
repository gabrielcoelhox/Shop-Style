package com.shopstyle.mspayment.rabbitmq.consumer;

import java.util.Optional;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.shopstyle.mspayment.dto.InstallmentDTO;
import com.shopstyle.mspayment.entities.Payment;
import com.shopstyle.mspayment.rabbitmq.publisher.PaymentOrderStatus;
import com.shopstyle.mspayment.rabbitmq.publisher.Status;
import com.shopstyle.mspayment.repository.PaymentRepository;
import com.shopstyle.mspayment.services.InstallmentService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RabbitMQConsumer {

	@Bean
	public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
		return new Jackson2JsonMessageConverter();
	}
	
	@Value("${mq.queues.payment-order}")
	private String queuePaymentOrder;
	
	private final PaymentRepository paymentRepository;
	
	private final InstallmentService installmentService;
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@RabbitListener(queues = "${mq.queues.order-payment}")
	private void processMessage(PaymentOrder paymentOrder) {
		Status status = statusCheck(paymentOrder);
		rabbitTemplate.convertAndSend(queuePaymentOrder, new PaymentOrderStatus(paymentOrder.getOrderId(), status));
	}
	
	private Status statusCheck(PaymentOrder paymentOrder) {
		
		Optional<Payment> optPayment = paymentRepository.findById(paymentOrder.getPayment().getId());
		InstallmentDTO installment = installmentService.findByPaymentId(paymentOrder.getPayment().getId());
		Status status = Status.PAYMENT_SUCCESSFUL;
		if(optPayment.isPresent()) {
			if(optPayment.get().isActive()) {
				if (paymentOrder.getPayment().getInstallments() > 0) {
					if(!optPayment.get().isInstallments()) {
						status = Status.PAYMENT_NOT_INSTALLMENT;
					} else if (paymentOrder.getPayment().getInstallments() <= installment.getAmount()) {
						status = Status.PAYMENT_SUCCESSFUL;
					} else {
						status = Status.PAYMENT_AMOUNT_NOT_AVAILABLE;
					}
				} else {
					status = Status.PAYMENT_SUCCESSFUL;
				}
			} else {
				status = Status.PAYMENT_INACTIVE;
			}
		} else {
			status = Status.PAYMENT_NOT_FOUND;
		}
		return status;
	}
}
