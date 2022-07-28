package com.shopstyle.msbffshop.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.shopstyle.msbffshop.clients.CustomerClient;
import com.shopstyle.msbffshop.clients.entities.Customer;
import com.shopstyle.msbffshop.clients.exceptions.DefaultException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerDetailsServiceImpl implements UserDetailsService {

	private final CustomerClient customerClient;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Customer customer = customerClient.findCustomerByEmail(username);
		if (customer == null) {
			throw new DefaultException("User login " + username + " not found.", "NOT_FOUND", 404);
		}
		return new CustomerDetailsData(customer);
	}
}
