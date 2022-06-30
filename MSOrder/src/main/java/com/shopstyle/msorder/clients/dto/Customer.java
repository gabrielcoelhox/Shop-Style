package com.shopstyle.msorder.clients.dto;

import java.time.LocalDate;

import com.shopstyle.msorder.clients.enums.Sex;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Customer {

	private Long id;
	private String firstName;
	private String lastName;
	private Sex sex;
	private LocalDate birthdate;
	private String email;
	private boolean active;
}
