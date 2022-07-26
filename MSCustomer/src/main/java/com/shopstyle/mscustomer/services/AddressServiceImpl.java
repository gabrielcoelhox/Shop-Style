package com.shopstyle.mscustomer.services;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.shopstyle.mscustomer.dto.AddressDTO;
import com.shopstyle.mscustomer.dto.AddressFormDTO;
import com.shopstyle.mscustomer.entities.Address;
import com.shopstyle.mscustomer.entities.Customer;
import com.shopstyle.mscustomer.exceptions.DefaultException;
import com.shopstyle.mscustomer.repository.AddressRepository;
import com.shopstyle.mscustomer.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

	private final AddressRepository addressRepository;
	
	private final CustomerRepository customerRepository;
	
	@Override
	public AddressDTO findById(Long id) {
		return new AddressDTO(addressRepository.findById(id).orElseThrow(
				() -> new DefaultException("Address with Id: " + id + " not found. Enter a valid ID.", "NOT_FOUND", 404)));
	}
	
	@Override
	public AddressDTO insert(@Valid AddressFormDTO form) {
		Customer customer = customerRepository.findById(form.getCustomerId()).orElseThrow(
				() -> new DefaultException("Customer with id: " + form.getCustomerId() + " not found. Enter a valid ID.", "NOT_FOUND", 404));
		
		return new AddressDTO(addressRepository.save(new Address(form, customer)));
	}
	
	@Override
	public AddressDTO update(Long id, @Valid AddressFormDTO form) {
		Address address = addressRepository.findById(id).orElseThrow(
				() -> new DefaultException("Address with id: " + form.getCustomerId() + " not found. Enter a valid ID.", "NOT_FOUND", 404));
		address.setStreet(form.getStreet());
		address.setNumber(form.getNumber());
		address.setComplement(form.getComplement());
		address.setDistrict(form.getDistrict());
		address.setCity(form.getCity());
		address.setState(form.getState());
		address.setCep(form.getCep());
		
		return new AddressDTO(addressRepository.save(address));
	}
	
	@Override
	public void deleteById(Long id) {
		addressRepository.findById(id).orElseThrow(
				() -> new DefaultException("Address with id: " + id + " not found. Enter a valid ID.", "NOT_FOUND", 404));
		addressRepository.deleteById(id);
	}
}
