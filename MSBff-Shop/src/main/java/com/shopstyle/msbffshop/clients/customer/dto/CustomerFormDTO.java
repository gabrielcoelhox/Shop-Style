package com.shopstyle.msbffshop.clients.customer.dto;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shopstyle.msbffshop.clients.enums.Sex;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CustomerFormDTO {

	@NotNull (message = "firstName field cannot be null")
	@Length(min = 3)
	private String firstName;
	
	@NotNull (message = "lastName field cannot be null")
	@Length(min = 3)
	private String lastName;

	@NotNull (message = "sex field cannot be null")
	private Sex sex;

	@NotNull (message = "cpf field cannot be null")
	@CPF
	private String cpf;
	
	@NotNull (message = "birthDate field cannot be null")
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate birthdate;
	
	@Email
	@NotNull (message = "email field cannot be null")
	private String email;
	
	@NotNull (message = "password field cannot be null")
	@Length(min = 6)
	private String password;
	
	@NotNull
	private boolean active;

	public void setPassword(String password) {
		this.password = password;
	}
}
