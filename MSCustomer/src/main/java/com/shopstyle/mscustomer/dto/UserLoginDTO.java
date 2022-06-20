package com.shopstyle.mscustomer.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginDTO {

	@NotNull
	@NotEmpty
	private String email;
	
	@NotNull
	@NotEmpty
	private String password;
}
