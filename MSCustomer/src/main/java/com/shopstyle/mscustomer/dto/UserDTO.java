package com.shopstyle.mscustomer.dto;

import java.time.LocalDate;

import com.shopstyle.mscustomer.entities.User;
import com.shopstyle.mscustomer.enums.Sex;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {
	
	private String firstName;
	private String lastName;
	private Sex sex;
	private LocalDate birthDate;
	private String email;
	private boolean active;

	public UserDTO(User usuarioObj) {
		this.firstName = usuarioObj.getFirstName();
		this.lastName = usuarioObj.getLastName();
		this.sex = usuarioObj.getSex();
		this.birthDate = usuarioObj.getBirthDate();
		this.email = usuarioObj.getEmail();
		this.active = usuarioObj.isActive();
	}
}
