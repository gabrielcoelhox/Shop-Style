package com.shopstyle.mspayment.services;

import java.util.List;

import javax.validation.Valid;

import com.shopstyle.mspayment.dto.PaymentDTO;
import com.shopstyle.mspayment.dto.PaymentFormDTO;

public interface PaymentService {

	List<PaymentDTO> findAll();
	
	PaymentDTO findById(Long id);

	PaymentDTO insert(@Valid PaymentFormDTO paymentForm);

	PaymentDTO update(Long id, @Valid PaymentFormDTO paymentForm);

	void deleteById(Long id);
}
