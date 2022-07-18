package com.shopstyle.mspayment.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.shopstyle.mspayment.dto.PaymentDTO;
import com.shopstyle.mspayment.dto.PaymentFormDTO;
import com.shopstyle.mspayment.entities.Payment;
import com.shopstyle.mspayment.exceptions.DefaultException;
import com.shopstyle.mspayment.repository.PaymentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

	private final PaymentRepository paymentRepository;

	public List<PaymentDTO> findAll() {
		return paymentRepository.findAll().stream().map(PaymentDTO::new).collect(Collectors.toList());
	}
	
	public PaymentDTO findById(Long id) {
		return new PaymentDTO(paymentRepository.findById(id).orElseThrow(
				() -> new DefaultException("Payment with ID: " + id + " not found. Enter a valid ID.", "NOT_FOUND", 404)));
	}

	public PaymentDTO insert(@Valid PaymentFormDTO payForm) {
		return new PaymentDTO(paymentRepository.save(new Payment(payForm)));
	}

	public PaymentDTO update(Long id, @Valid PaymentFormDTO payForm) {
		Payment payment = paymentRepository.findById(id).orElseThrow(
				() -> new DefaultException("Payment with ID: " + id + " not found. Enter a valid ID.", "NOT_FOUND", 404));
		payment.setType(payForm.getType());
		payment.setActive(payForm.isActive());
		payment.setInstallments(payForm.isInstallments());
		
		return new PaymentDTO(payment);
	}

	public void deleteById(Long id) {
		paymentRepository.findById(id).orElseThrow(
				() -> new DefaultException("Payment with ID: " + id + " not found. Enter a valid ID.", "NOT_FOUND", 404));
		paymentRepository.deleteById(id);
	}
}
