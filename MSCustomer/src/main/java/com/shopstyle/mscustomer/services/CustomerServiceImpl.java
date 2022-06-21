package com.shopstyle.mscustomer.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.shopstyle.mscustomer.entities.Customer;
import com.shopstyle.mscustomer.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements UserDetailsService {

	@Lazy
    @Autowired
    private PasswordEncoder encoder;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Transactional
    public Customer save(Customer Customer) {
        return customerRepository.save(Customer);
	}

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Customer Customer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Email not found in database."));
		
		String[] roles = Customer.isActive() ?
                new String[]{"ACTIVE", "USER"} : new String[]{"USER"};
		
		return User
                .builder()
                .username(Customer.getEmail())
                .password(Customer.getPassword())
                .roles(roles)
                .build();
    }

}
