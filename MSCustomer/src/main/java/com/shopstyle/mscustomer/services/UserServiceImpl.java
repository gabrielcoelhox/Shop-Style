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

import com.shopstyle.mscustomer.entities.Usuario;
import com.shopstyle.mscustomer.repository.UserRepository;

@Service
public class UserServiceImpl implements UserDetailsService {

	@Lazy
    @Autowired
    private PasswordEncoder encoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Transactional
    public Usuario save(Usuario usuario) {
        return userRepository.save(usuario);
	}

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuario usuario = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Email not found in database."));
		
		String[] roles = usuario.isActive() ?
                new String[]{"ACTIVE", "USER"} : new String[]{"USER"};
		
		return User
                .builder()
                .username(usuario.getEmail())
                .password(usuario.getPassword())
                .roles(roles)
                .build();
    }

}
