package com.shopstyle.mscustomer.dto;

import java.time.LocalDate;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shopstyle.mscustomer.entities.User;
import com.shopstyle.mscustomer.enums.Sex;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserDTO {
	
	@NotNull
	@NotEmpty (message = "firstName field cannot be empty")
	private String firstName;
	
	@NotNull
	@NotEmpty (message = "lastName field cannot be empty")
	private String lastName;
	
	@Enumerated(EnumType.STRING)
	private Sex sex;
	
	@CPF
	@NotNull
	@NotEmpty (message = "cpf field cannot be empty")
	private String cpf;
	
	@NotNull 
	@NotEmpty (message = "birthDate field cannot be empty")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate birthDate;
	
	@Email
	@NotNull
	@NotEmpty (message = "email field cannot be empty")
	private String email;
	
	@NotNull
	@NotEmpty (message = "password field cannot be empty")
	private String password;
	
	@NotNull
	private boolean active;

	public UserDTO(User usuarioObj) {
		this.firstName = usuarioObj.getFirstName();
		this.lastName = usuarioObj.getLastName();
		this.sex = usuarioObj.getSex();
		this.cpf = usuarioObj.getCpf();
		this.birthDate = usuarioObj.getBirthDate();
		this.email = usuarioObj.getEmail();
		this.password = usuarioObj.getPassword();
		this.active = usuarioObj.isActive();
	}
}
