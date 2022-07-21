package com.shopstyle.msbffshop.clients.entities;

import java.io.Serializable;
import java.time.LocalDate;

import com.shopstyle.msbffshop.clients.enums.Sex;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String firstName;
	private String lastName;
	private Sex sex;
	private LocalDate birthdate;
	private String email;
	private boolean active;
}
