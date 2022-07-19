package com.shopstyle.mscustomer.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shopstyle.mscustomer.dto.CustomerFormDTO;
import com.shopstyle.mscustomer.enums.Sex;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customer")
@Data
@NoArgsConstructor
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull @Length(min = 3)
	private String firstName;
	
	@NotNull @Length(min = 3)
	private String lastName;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private Sex sex;
	
	@CPF
	@NotNull
	private String cpf;
	
	@NotNull
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate birthDate;
	
	@Email
	@Column(unique = true)
	private String email;
	
	@NotNull
	@Length(min = 6)
	private String password;
	
	@NotNull
	private boolean active;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
	private List<Address> addresses;
	
	public Customer(CustomerFormDTO form) {
		this.firstName = form.getFirstName();
		this.lastName = form.getLastName();
		this.sex = form.getSex();
		this.cpf = form.getCpf();
		this.birthDate = form.getBirthdate();
		this.email = form.getEmail();
		this.password = new BCryptPasswordEncoder().encode(form.getPassword());
		this.active = form.isActive();
	}
}
