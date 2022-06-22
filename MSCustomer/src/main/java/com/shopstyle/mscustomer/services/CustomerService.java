package com.shopstyle.mscustomer.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.shopstyle.mscustomer.dto.CustomerDTO;
import com.shopstyle.mscustomer.dto.CustomerFormDTO;
import com.shopstyle.mscustomer.dto.CustomerLoginDTO;
import com.shopstyle.mscustomer.entities.Customer;
import com.shopstyle.mscustomer.exceptions.DefaultException;
import com.shopstyle.mscustomer.exceptions.InvalidPasswordException;
import com.shopstyle.mscustomer.exceptions.MethodArgumentNotValidException;
import com.shopstyle.mscustomer.repository.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Transactional
	public List<CustomerDTO> findAll() {
		List<Customer> list = customerRepository.findAll();
		return userListDTO(list);
	}
	
	@Transactional
	public CustomerDTO findById(Long id) {
		Customer userObj = customerRepository.findById(id).orElseThrow(
				() -> new DefaultException("User with id " + id + " not found, enter a valid id", "NOT_FOUND", 404));
		return new CustomerDTO(userObj);
	}
	
	public CustomerDTO insert(@Valid CustomerFormDTO CustomerFormDTO) {
		Customer Customer = new Customer(CustomerFormDTO);
		try {
			customerRepository.save(Customer);
			return new CustomerDTO(Customer);
		} catch (com.shopstyle.mscustomer.exceptions.MethodArgumentNotValidException e) {
			throw new com.shopstyle.mscustomer.exceptions.MethodArgumentNotValidException(e.getMessage());
		}
	}
	
	public CustomerDTO update(@Valid CustomerFormDTO CustomerFormDTO, Long id) {
		Customer newUser = customerRepository.findById(id).orElseThrow(
				() -> new DefaultException("User with id " + id + " not found, enter a valid id", "NOT_FOUND", 404));
		try {
			newUser.setFirstName(CustomerFormDTO.getFirstName());
			newUser.setLastName(CustomerFormDTO.getLastName());
			newUser.setSex(CustomerFormDTO.getSex());
			newUser.setCpf(CustomerFormDTO.getCpf());
			newUser.setBirthDate(CustomerFormDTO.getBirthdate());
			newUser.setEmail(CustomerFormDTO.getEmail());
			newUser.setPassword(CustomerFormDTO.getPassword());
			newUser.setActive(CustomerFormDTO.isActive());
			
			CustomerDTO CustomerDTO = new CustomerDTO();
			CustomerDTO.setFirstName(newUser.getFirstName());
			CustomerDTO.setLastName(newUser.getLastName());
			CustomerDTO.setSex(newUser.getSex());
			CustomerDTO.setBirthdate(newUser.getBirthDate());
			CustomerDTO.setEmail(newUser.getEmail());
			CustomerDTO.setActive(newUser.isActive());		
			return CustomerDTO;		
		}catch (MethodArgumentNotValidException e) {
			throw new MethodArgumentNotValidException(e.getMessage());
		}
	}
	
	public CustomerDTO login(CustomerLoginDTO customerLoginDto) {
		Customer customer = customerRepository.findByEmail(customerLoginDto.getEmail());
		if(customer != null && new BCryptPasswordEncoder().matches(customerLoginDto.getPassword(), customer.getPassword())) {	
			return new CustomerDTO(customer);		
		}
		throw new InvalidPasswordException();
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
