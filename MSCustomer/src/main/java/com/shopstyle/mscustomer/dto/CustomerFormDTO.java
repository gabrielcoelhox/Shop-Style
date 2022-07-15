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
	@Enumerated(EnumType.STRING)
	private Sex sex;

	@NotNull (message = "cpf field cannot be null")
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
	@Length(min = 6)
	private String password;
	
	@NotNull
	private boolean active;

	public void setPassword(String password) {
		this.password = password;
	}
}
