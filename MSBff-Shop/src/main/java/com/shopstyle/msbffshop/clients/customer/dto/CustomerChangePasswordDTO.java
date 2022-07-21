package com.shopstyle.msbffshop.clients.customer.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerChangePasswordDTO {

	@NotNull @Email
	private String email;
	
	@CPF @NotNull
	private String cpf;
	
	@NotNull @NotEmpty
	private String oldPassword;
	
	@NotNull @Length(min = 6)
	private String newPassword;
	
	@NotNull @Length(min = 6)
	private String confirmNewPassword;
}
