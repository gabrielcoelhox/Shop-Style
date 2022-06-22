package com.shopstyle.mscustomer.dto;

import java.time.LocalDate;

import com.shopstyle.mscustomer.entities.Customer;
import com.shopstyle.mscustomer.enums.Sex;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CustomerDTO {
	
	private Long id;
	private String firstName;
	private String lastName;
	private Sex sex;
	private LocalDate birthdate;
	private String email;
	private boolean active;

	public CustomerDTO(Customer customer) {
		this.id = customer.getId();
		this.firstName = customer.getFirstName();
		this.lastName = customer.getLastName();
		this.sex = customer.getSex();
		this.birthdate = customer.getBirthDate();
		this.email = customer.getEmail();
		this.active = customer.isActive();
	}
}
