package com.shopstyle.mscustomer.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.shopstyle.mscustomer.dto.UserDTO;
import com.shopstyle.mscustomer.dto.UserFormDTO;
import com.shopstyle.mscustomer.entities.User;
import com.shopstyle.mscustomer.exceptions.DefaultException;
import com.shopstyle.mscustomer.exceptions.InvalidPasswordException;
import com.shopstyle.mscustomer.exceptions.MethodArgumentNotValidException;
import com.shopstyle.mscustomer.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

	@Lazy
	@Autowired
    private PasswordEncoder encoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Transactional
    public User salvar(User usuario) {
        return userRepository.save(usuario);
	}
	
	public UserDetails authenticate(User usuario) {
        UserDetails user = loadUserByUsername(usuario.getEmail());
        boolean senhasBatem = encoder.matches(usuario.getPassword(), user.getPassword());

        if(senhasBatem) {
            return user;
        }
        throw new InvalidPasswordException();
    }
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User usuario = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Email not found in database."));
		
		String[] roles = usuario.isActive() ?
                new String[]{"ADMIN", "USER"} : new String[]{"USER"};

        return User
                .builder()
                .email(usuario.getEmail())
                .password(usuario.getPassword())
                .roles(roles)
                .build();
	}
	
	@Transactional
	public List<UserDTO> findAll() {
		List<User> list = userRepository.findAll();
		return userListDTO(list);
	}
	
	@Transactional
	public UserDTO findById(Long id) {
		User userObj = userRepository.findById(id).orElseThrow(
				() -> new DefaultException("User with id " + id + " not found, enter a valid id", "NOT_FOUND", 404));
		return new UserDTO(userObj);
	}
	
	public UserDTO insert(@Valid UserFormDTO userFormDTO) {
		User usuario = new User(userFormDTO);
		try {
			userRepository.save(usuario);
			return new UserDTO(usuario);
		} catch (com.shopstyle.mscustomer.exceptions.MethodArgumentNotValidException e) {
			throw new com.shopstyle.mscustomer.exceptions.MethodArgumentNotValidException(e.getMessage());
		}
	}
	
	public UserDTO update(@Valid UserFormDTO userFormDTO, Long id) {
		User newUser = userRepository.findById(id).orElseThrow(
				() -> new DefaultException("User with id " + id + " not found, enter a valid id", "NOT_FOUND", 404));
		try {
			newUser.setFirstName(userFormDTO.getFirstName());
			newUser.setLastName(userFormDTO.getLastName());
			newUser.setSex(userFormDTO.getSex());
			newUser.setCpf(userFormDTO.getCpf());
			newUser.setBirthDate(userFormDTO.getBirthdate());
			newUser.setEmail(userFormDTO.getEmail());
			newUser.setPassword(userFormDTO.getPassword());
			newUser.setActive(userFormDTO.isActive());
			
			UserDTO userDTO = new UserDTO();
			userDTO.setFirstName(newUser.getFirstName());
			userDTO.setLastName(newUser.getLastName());
			userDTO.setSex(newUser.getSex());
			userDTO.setBirthDate(newUser.getBirthDate());
			userDTO.setEmail(newUser.getEmail());
			userDTO.setActive(newUser.isActive());		
			return userDTO;		
		}catch (MethodArgumentNotValidException e) {
			throw new MethodArgumentNotValidException(e.getMessage());
		}
	}
	
	public static List<UserDTO> userListDTO(List<User> list) {
		List<UserDTO> listDTO = new ArrayList<>();
		for (User user : list) {
			UserDTO dto = new UserDTO();
			dto.setFirstName(user.getFirstName());
			dto.setLastName(user.getLastName());
			dto.setSex(user.getSex());
			dto.setBirthDate(user.getBirthDate());
			dto.setEmail(user.getEmail());
			dto.setActive(user.isActive());

			listDTO.add(dto);
		}
		return listDTO;
	}
}
