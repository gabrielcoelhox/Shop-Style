package com.shopstyle.mspayment.services;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopstyle.mspayment.dto.InstallmentDTO;
import com.shopstyle.mspayment.dto.InstallmentFormDTO;
import com.shopstyle.mspayment.entities.Installment;
import com.shopstyle.mspayment.entities.Payment;
import com.shopstyle.mspayment.exceptions.MethodArgumentNotValidException;
import com.shopstyle.mspayment.repository.InstallmentRepository;
import com.shopstyle.mspayment.repository.PaymentRepository;

@Service
public class InstallmentService {

	@Autowired
	private InstallmentRepository installmentRepository;
	
	@Autowired
	private PaymentRepository paymentRepository;

	public InstallmentDTO insert(@Valid InstallmentFormDTO installmentForm) {
		Payment payment = paymentRepository.findById(installmentForm.getPaymentId()).orElseThrow(
				() -> new MethodArgumentNotValidException("Payment with ID: " + installmentForm.getPaymentId() + " not found. Enter a valid ID."));
		
		if(payment.isActive() && payment.isInstallments()) {
			return new InstallmentDTO(installmentRepository.save(new Installment(installmentForm, payment)));
		} else {
			throw new MethodArgumentNotValidException("The payment method chosen is not valid.");
		}	
	}

	public InstallmentDTO update(Long id, @Valid InstallmentFormDTO installmentForm) {
		Installment installment = installmentRepository.findById(id).orElseThrow(
				() -> new MethodArgumentNotValidException("Installment with ID: " + id + " not found. Enter a valid ID."));
		Payment payment = paymentRepository.findById(installmentForm.getPaymentId()).orElseThrow(
				() -> new MethodArgumentNotValidException("Payment with ID " + installmentForm.getPaymentId() + " not found. Enter a valid ID."));
		installment.setPayment(payment);
		installment.setAmount(installmentForm.getAmount());
		installment.setBrand(installmentForm.getBrand());
		return new InstallmentDTO(installment);
	}

	public void deleteById(Long id) {
		installmentRepository.findById(id).orElseThrow(
				() -> new MethodArgumentNotValidException("Installment with ID: " + id + " not found. Enter a valid ID."));
		installmentRepository.deleteById(id);
	}
}