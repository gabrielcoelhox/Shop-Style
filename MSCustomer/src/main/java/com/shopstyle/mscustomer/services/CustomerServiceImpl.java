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
import com.shopstyle.mscustomer.dto.CustomerUpdateDTO;
import com.shopstyle.mscustomer.entities.Customer;
import com.shopstyle.mscustomer.exceptions.DefaultException;
import com.shopstyle.mscustomer.exceptions.LoginException;
import com.shopstyle.mscustomer.exceptions.MethodArgumentNotValidException;
import com.shopstyle.mscustomer.exceptions.ObjectNotFoundException;
import com.shopstyle.mscustomer.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
	
	private final CustomerRepository customerRepository;
	
	@Transactional
	public List<CustomerDTO> findAll() {
		List<Customer> list = customerRepository.findAll();
		return userListDTO(list);
	}
	
	@Transactional
	@Override
	public CustomerDTO findById(Long id) {
		Customer customerObj = customerRepository.findById(id).orElseThrow(
				() -> new DefaultException("Customer with id " + id + " not found, enter a valid id", "NOT_FOUND", 404));
		return new CustomerDTO(customerObj);
	}
	
	@Override
	public Customer findByEmail(String email) {
		return customerRepository.findByEmail(email);
	}
	
	@Override
	public CustomerDTO insert(@Valid CustomerFormDTO customerForm) {
		Customer customer = new Customer(customerForm);
		try {
			customerRepository.save(customer);
			return new CustomerDTO(customer);
		} catch (MethodArgumentNotValidException ex) {
			throw new ObjectNotFoundException(ex.getMessage());
		}
	}
	
	@Override
	public CustomerDTO update(@Valid CustomerUpdateDTO customerUpdate, Long id) {
		Customer newCustomer = customerRepository.findById(id).orElseThrow(
				() -> new DefaultException("Customer with id " + id + " not found, enter a valid id", "NOT_FOUND", 404));
		try {
			newCustomer.setFirstName(customerUpdate.getFirstName());
			newCustomer.setLastName(customerUpdate.getLastName());
			newCustomer.setSex(customerUpdate.getSex());
			newCustomer.setCpf(customerUpdate.getCpf());
			newCustomer.setBirthDate(customerUpdate.getBirthdate());
			newCustomer.setActive(customerUpdate.isActive());	
			return new CustomerDTO(newCustomer);		
		} catch (MethodArgumentNotValidException ex) {
			throw new ObjectNotFoundException(ex.getMessage());
		}
	}
	
	@Override
	public CustomerDTO login(CustomerLoginDTO customerLogin) {
		Customer loginCustomer = customerRepository.findByEmail(customerLogin.getEmail());
		if(loginCustomer != null && new BCryptPasswordEncoder().matches(customerLogin.getPassword(), loginCustomer.getPassword())) {	
			return new CustomerDTO(loginCustomer);		
		}
		throw new LoginException("Login or password incorrect.");
	}
	
	@Override
	public CustomerDTO changePassword(@Valid CustomerChangePasswordDTO passwordDTO, Long id) {
		Customer changePass = customerRepository.findById(id).orElseThrow(
				() -> new DefaultException("Customer with id " + id + " not found, enter a valid id", "NOT_FOUND", 404));
		if(verificationPassword(changePass, passwordDTO)) {	
			changePass.setPassword(passwordDTO.getNewPassword());
			return new CustomerDTO(changePass);		
		}
		throw new MethodArgumentNotValidException("Any of the data entered is incorrect.");
	}
	
	private boolean verificationPassword(Customer customerPassword, CustomerChangePasswordDTO passwordDTO) {
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
