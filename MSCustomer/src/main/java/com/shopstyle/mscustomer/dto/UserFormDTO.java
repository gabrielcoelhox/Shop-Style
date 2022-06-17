package com.shopstyle.mscustomer.dto;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shopstyle.mscustomer.enums.Sex;

import lombok.Getter;

@Getter
public class UserFormDTO {
	
	private Long id;

	@NotNull (message = "firstName field cannot be null")
	@Length(min = 3)
	private String firstName;
	
	@NotNull (message = "lastName field cannot be null")
	@Length(min = 3)
	private String lastName;

	@Enumerated(EnumType.STRING)
	private Sex sex;

	@CPF
	private String cpf;
	
	@NotNull (message = "birthDate field cannot be null")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate birthdate;
	
	@Email
	@Column(unique = true)
	private String email;
	
	@NotNull
	@NotEmpty (message = "password field cannot be empty")
	@Length(min = 8)
	private String password;
	
	@NotNull
	private boolean active;

	public void setPassword(String password) {
		this.password = password;
	}
}
