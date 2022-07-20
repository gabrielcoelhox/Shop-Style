package com.shopstyle.msaudit.clients.dto;

import java.time.LocalDate;

import com.shopstyle.msaudit.clients.enums.Sex;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Customer {

	private Long id;
	private String firstName;
	private String lastName;
	private Sex sex;
	private LocalDate birthdate;
	private String email;
	private boolean active;
}
