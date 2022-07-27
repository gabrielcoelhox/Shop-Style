package com.shopstyle.mspayment.services;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.shopstyle.mspayment.dto.InstallmentDTO;
import com.shopstyle.mspayment.dto.InstallmentFormDTO;
import com.shopstyle.mspayment.entities.Installment;
import com.shopstyle.mspayment.entities.Payment;
import com.shopstyle.mspayment.exceptions.DefaultException;
import com.shopstyle.mspayment.exceptions.PaymentNotValidException;
import com.shopstyle.mspayment.repository.InstallmentRepository;
import com.shopstyle.mspayment.repository.PaymentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InstallmentServiceImpl implements InstallmentService {

	private final InstallmentRepository installmentRepository;

	private final PaymentRepository paymentRepository;
	
	@Override
	public InstallmentDTO findByPaymentId(Long paymanetId) {
		Installment installment = installmentRepository.findByPaymentId(paymanetId);
		if(installment != null) {
			return new InstallmentDTO(installment);
		}
		throw new DefaultException("Payment with ID: " + paymanetId + " not found. Enter a valid ID.", "NOT_FOUND", 404);	
	}

	@Override
	public InstallmentDTO insert(@Valid InstallmentFormDTO form) {
		Payment payment = paymentRepository.findById(form.getPaymentId()).orElseThrow(
				() -> new DefaultException("Payment with ID: " + form.getPaymentId() + " not found. Enter a valid ID.", "NOT_FOUND", 404));
		
		if(payment.isActive() && payment.isInstallments()) {
			return new InstallmentDTO(installmentRepository.save(new Installment(form, payment)));
		} else {
			throw new PaymentNotValidException("The payment method chosen is not valid.");
		}	
	}

	@Override
	public InstallmentDTO update(Long id, @Valid InstallmentFormDTO form) {
		Installment installment = installmentRepository.findById(id).orElseThrow(
				() -> new DefaultException("Installment with ID: " + id + " not found. Enter a valid ID.", "NOT_FOUND", 404));
		Payment payment = paymentRepository.findById(form.getPaymentId()).orElseThrow(
				() -> new DefaultException("Payment with ID " + form.getPaymentId() + " not found. Enter a valid ID.", "NOT_FOUND", 404));
		installment.setPayment(payment);
		installment.setAmount(form.getAmount());
		installment.setBrand(form.getBrand());
		return new InstallmentDTO(installment);
	}

	@Override
	public void deleteById(Long id) {
		installmentRepository.findById(id).orElseThrow(
				() -> new DefaultException("Installment with ID: " + id + " not found. Enter a valid ID.", "NOT_FOUND", 404));
		installmentRepository.deleteById(id);
	}
}
