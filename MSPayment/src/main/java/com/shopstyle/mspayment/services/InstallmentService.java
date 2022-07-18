package com.shopstyle.mspayment.services;

import javax.validation.Valid;

import com.shopstyle.mspayment.dto.InstallmentDTO;
import com.shopstyle.mspayment.dto.InstallmentFormDTO;

public interface InstallmentService {

	InstallmentDTO insert(@Valid InstallmentFormDTO installmentForm);

	InstallmentDTO update(Long id, @Valid InstallmentFormDTO installmentForm);
	
	void deleteById(Long id);
}
