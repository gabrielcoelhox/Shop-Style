package com.shopstyle.mscustomer.services;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopstyle.mscustomer.dto.AddressDTO;
import com.shopstyle.mscustomer.dto.AddressFormDTO;
import com.shopstyle.mscustomer.entities.Address;
import com.shopstyle.mscustomer.entities.Customer;
import com.shopstyle.mscustomer.exceptions.DefaultException;
import com.shopstyle.mscustomer.repository.AddressRepository;
import com.shopstyle.mscustomer.repository.CustomerRepository;

@Service
public class AddressService {

	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	public AddressDTO insert(@Valid AddressFormDTO addressFormDto) {
		Customer customer = customerRepository.findById(addressFormDto.getCustomerId()).orElseThrow(
				() -> new DefaultException("Customer with id " + addressFormDto.getCustomerId() + " not found. Enter a valid ID.", "NOT_FOUND", 404));
		
		Address address = new Address();
		address.setStreet(addressFormDto.getStreet());
		address.setNumber(addressFormDto.getNumber());
		address.setComplement(addressFormDto.getComplement());
		address.setDistrict(addressFormDto.getDistrict());
		address.setCity(addressFormDto.getCity());
		address.setState(addressFormDto.getState());
		address.setCep(addressFormDto.getCep());
		address.setCustomer(customer);	
		
		return new AddressDTO(addressRepository.save(address));
	}

	public AddressDTO update(Long id, @Valid AddressFormDTO addressFormDto) {
		Address address = addressRepository.findById(id).orElseThrow(
				() -> new DefaultException("Address with id: " + addressFormDto.getCustomerId() + " not found. Enter a valid ID.", "NOT_FOUND", 404));
		address.setStreet(addressFormDto.getStreet());
		address.setNumber(addressFormDto.getNumber());
		address.setComplement(addressFormDto.getComplement());
		address.setDistrict(addressFormDto.getDistrict());
		address.setCity(addressFormDto.getCity());
		address.setState(addressFormDto.getState());
		address.setCep(addressFormDto.getCep());
		
		return new AddressDTO(addressRepository.save(address));
	}
	
	public void deleteById(Long id) {
		addressRepository.findById(id).orElseThrow(
				() -> new DefaultException("Address with id: " + id + " not found. Enter a valid ID.", "NOT_FOUND", 404));
		addressRepository.deleteById(id);
	}
}
