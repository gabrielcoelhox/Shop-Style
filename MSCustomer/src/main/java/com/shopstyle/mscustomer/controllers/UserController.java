package com.shopstyle.mscustomer.controllers;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopstyle.mscustomer.dto.UserDTO;
import com.shopstyle.mscustomer.dto.UserFormDTO;
import com.shopstyle.mscustomer.services.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/v1/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@ApiOperation(value= "Retorn all users")
	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll() {
		List<UserDTO> listUsuarioDTO = userService.findAll();
		return ResponseEntity.ok().body(listUsuarioDTO);
	}
	
	@ApiOperation(value= "Returns a unique user by id")	
	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
		return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
	}
	
	@PostMapping
	@Transactional
	@ApiOperation(value = "Insert a new user")
	public ResponseEntity<UserDTO> insert(@RequestBody @Valid UserFormDTO insertUser) {
		return new ResponseEntity<>(userService.insert(insertUser), HttpStatus.CREATED);
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
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<UserDTO> updated(@RequestBody @Valid UserFormDTO userFormDto, @PathVariable Long id) {
		return new ResponseEntity<>(userService.update(userFormDto, id), HttpStatus.OK);
	}
}
