package com.shopstyle.mscustomer.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shopstyle.mscustomer.dto.UserDTO;
import com.shopstyle.mscustomer.enums.Sex;

import lombok.Data;

@Entity
@Table(name = "tb_user")
@Data
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull @NotEmpty
	private String firstName;
	
	@NotNull @NotEmpty
	private String lastName;
	
	@Enumerated(EnumType.STRING)
	private Sex Sex;
	
	@CPF
	private String cpf;
	
	@NotNull @NotEmpty
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate birthDate;
	
	@Email
	@Column(unique = true)
	private String email;
	
	@NotNull @NotEmpty
	private String password;
	
	@NotNull
	private boolean active;
	
	public User() {}
	
	public User(UserDTO userDTO) {
		this.firstName = userDTO.getFirstName();
		this.lastName = userDTO.getLastName();
		this.Sex = userDTO.getSex();
		this.cpf = userDTO.getCpf();
		this.birthDate = userDTO.getBirthDate();
		this.email = userDTO.getEmail();
		this.password = userDTO.getPassword();
		this.active = userDTO.isActive();
	}
}
