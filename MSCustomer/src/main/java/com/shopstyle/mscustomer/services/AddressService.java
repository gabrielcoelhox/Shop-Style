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
	
	public AddressDTO save(@Valid AddressFormDTO addressFormDto) {
		Customer customer = customerRepository.findById(addressFormDto.getCustomerId()).orElseThrow(
				() -> new DefaultException("Customer Id: " + addressFormDto.getCustomerId() + " not found."));
		
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
				() -> new DefaultException("Address Id: " + addressFormDto.getCustomerId() + " not found."));
		address.setStreet(addressFormDto.getStreet());
		address.setNumber(addressFormDto.getNumber());
		address.setComplement(addressFormDto.getComplement());
		address.setDistrict(addressFormDto.getDistrict());
		address.setCity(addressFormDto.getCity());
		address.setState(addressFormDto.getState());
		address.setCep(addressFormDto.getCep());
		
		return new AddressDTO(addressRepository.save(address));
	}
}
