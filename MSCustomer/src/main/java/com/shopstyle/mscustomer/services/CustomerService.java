package com.shopstyle.mscustomer.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.shopstyle.mscustomer.dto.CustomerChangePasswordDTO;
import com.shopstyle.mscustomer.dto.CustomerDTO;
import com.shopstyle.mscustomer.dto.CustomerFormDTO;
import com.shopstyle.mscustomer.dto.CustomerLoginDTO;
import com.shopstyle.mscustomer.entities.Customer;
import com.shopstyle.mscustomer.exceptions.DefaultException;
import com.shopstyle.mscustomer.exceptions.InvalidPasswordException;
import com.shopstyle.mscustomer.exceptions.MethodArgumentNotValidException;
import com.shopstyle.mscustomer.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {
	
	private final CustomerRepository customerRepository;
	
	@Transactional
	public List<CustomerDTO> findAll() {
		List<Customer> list = customerRepository.findAll();
		return userListDTO(list);
	}
	
	@Transactional
	public CustomerDTO findById(Long id) {
		Customer customerObj = customerRepository.findById(id).orElseThrow(
				() -> new DefaultException("Customer with id " + id + " not found, enter a valid id", "NOT_FOUND", 404));
		return new CustomerDTO(customerObj);
	}
	
	public CustomerDTO insert(@Valid CustomerFormDTO customerForm) {
		Customer customer = new Customer(customerForm);
		try {
			customerRepository.save(customer);
			return new CustomerDTO(customer);
		} catch (com.shopstyle.mscustomer.exceptions.MethodArgumentNotValidException e) {
			throw new com.shopstyle.mscustomer.exceptions.MethodArgumentNotValidException(e.getMessage());
		}
	}
	
	public CustomerDTO update(@Valid CustomerFormDTO customerForm, Long id) {
		Customer newCustomer = customerRepository.findById(id).orElseThrow(
				() -> new DefaultException("Customer with id " + id + " not found, enter a valid id", "NOT_FOUND", 404));
		try {
			newCustomer.setFirstName(customerForm.getFirstName());
			newCustomer.setLastName(customerForm.getLastName());
			newCustomer.setSex(customerForm.getSex());
			newCustomer.setCpf(customerForm.getCpf());
			newCustomer.setBirthDate(customerForm.getBirthdate());
			newCustomer.setEmail(customerForm.getEmail());
			newCustomer.setPassword(customerForm.getPassword());
			newCustomer.setActive(customerForm.isActive());	
			return new CustomerDTO(newCustomer);		
		} catch (MethodArgumentNotValidException e) {
			throw new MethodArgumentNotValidException(e.getMessage());
		}
	}
	
	public CustomerDTO login(CustomerLoginDTO customerLogin) {
		Customer loginCustomer = customerRepository.findByEmail(customerLogin.getEmail());
		if(loginCustomer != null && new BCryptPasswordEncoder().matches(customerLogin.getPassword(), loginCustomer.getPassword())) {	
			return new CustomerDTO(loginCustomer);		
		}
		throw new InvalidPasswordException();
	}
	
	public CustomerDTO changePassword(@Valid CustomerChangePasswordDTO passwordDto, Long id) {
		Customer changePass = customerRepository.findById(id).orElseThrow(
				() -> new DefaultException("Customer with id: " + id + " not found, enter a valid id", "NOT_FOUND", 404));
		if(Boolean.TRUE.equals(verificationPassword(changePass, passwordDto))) {	
			changePass.setPassword(passwordDto.getNewPassword());
			return new CustomerDTO(changePass);		
		}
		throw new MethodArgumentNotValidException("Any of the data entered is incorrect.");
	}
	
	private Boolean verificationPassword(Customer customerPassword, CustomerChangePasswordDTO passwordDTO) {
		if(new BCryptPasswordEncoder().matches(passwordDTO.getOldPassword(), customerPassword.getPassword())
				&& passwordDTO.getCpf().equals(customerPassword.getCpf())
				&& passwordDTO.getEmail().equals(customerPassword.getEmail())
				&& passwordDTO.getNewPassword().equals(passwordDTO.getConfirmNewPassword())) {
			return true;
		}
		return false;
	}
	
	public static List<CustomerDTO> userListDTO(List<Customer> list) {
		List<CustomerDTO> listDTO = new ArrayList<>();
		for (Customer user : list) {
			CustomerDTO dto = new CustomerDTO();
			dto.setFirstName(user.getFirstName());
			dto.setLastName(user.getLastName());
			dto.setSex(user.getSex());
			dto.setBirthdate(user.getBirthDate());
			dto.setEmail(user.getEmail());
			dto.setActive(user.isActive());

			listDTO.add(dto);
		}
		return listDTO;
	}
}
