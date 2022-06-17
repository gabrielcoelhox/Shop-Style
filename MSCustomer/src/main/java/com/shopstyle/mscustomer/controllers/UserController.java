package com.shopstyle.mscustomer.controllers;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.shopstyle.mscustomer.dto.UserDTO;
import com.shopstyle.mscustomer.dto.UserFormDTO;
import com.shopstyle.mscustomer.services.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/v1")
public class UserController {

	@Autowired
	private UserService userService;
	
	private PasswordEncoder passwordEncoder;
	
	@ApiOperation(value= "Retorn all users")
	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll() {
		List<UserDTO> listUsuarioDTO = userService.findAll();
		return ResponseEntity.ok().body(listUsuarioDTO);
	}
	
	@ApiOperation(value= "Returns a unique user by id")	
	@GetMapping("/users/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
		return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
	}
	
	@Transactional
	@PostMapping("/users")
	@ApiOperation(value = "Insert a new user")
	public ResponseEntity<UserDTO> insert(@RequestBody @Valid UserFormDTO insertUser, UriComponentsBuilder uriComponentsBuilder) {
		String senhaCriptografada = passwordEncoder.encode(insertUser.getPassword());
		insertUser.setPassword(senhaCriptografada);
		
		URI uri = uriComponentsBuilder.path("/v1/users/{id}").buildAndExpand(insertUser.getId()).toUri();
		return ResponseEntity.created(uri).body(userService.insert(insertUser));
	}
	
	@ApiOperation(value= "Update a user")
	@ApiResponses({
	      @ApiResponse(code = 200, message = "Update done successfully",
	            response = UserDTO.class),
	      @ApiResponse(code = 403, message = "Profile not authorized to perform this operation",
			response = UserDTO.class),
	      @ApiResponse(code = 404, message = "User not found",
	            response = UserDTO.class)
	})
	@PutMapping("/users/{id}")
	@Transactional
	public ResponseEntity<UserDTO> update(@RequestBody @Valid UserFormDTO userFormDto, @PathVariable Long id) {
		return new ResponseEntity<>(userService.update(userFormDto, id), HttpStatus.OK);
	}
	
//	@PostMapping("/login")
//	public ResponseEntity<UserDTO> login(UserLoginDTO userLoginDto) {
//		return new ResponseEntity<>(userService.login(userLoginDto), HttpStatus.ACCEPTED);
//	}
	
//	@PostMapping("/login")
//    public TokenDTO authenticate(@RequestBody CredentialsDTO credentials) {
//        try {
//            User user = User.builder()
//                    .email(credentials.getEmail())
//                    .password(credentials.getPassword()).build();
//            UserDetails authenticatedUser = userService.authenticate(user);
//            String token = jwtService.gerarToken(user);
//            return new TokenDTO(user.getEmail(), token);
//        } catch (UsernameNotFoundException | InvalidPasswordException ex) {
//            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ex.getMessage());
//        }
//    }
}
