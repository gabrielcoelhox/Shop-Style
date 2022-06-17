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

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shopstyle.mscustomer.dto.UserFormDTO;
import com.shopstyle.mscustomer.enums.Sex;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_user")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull @Length(min = 3)
	private String firstName;
	
	@NotNull @Length(min = 3)
	private String lastName;
	
	@Enumerated(EnumType.STRING)
	private Sex Sex;
	
	@CPF
	private String cpf;
	
	@NotNull
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate birthDate;
	
	@Email
	@Column(unique = true)
	private String email;
	
	@NotNull @NotEmpty
	@Length(min = 8)
	private String password;
	
	@NotNull
	private boolean active;
	
	public User(UserFormDTO userFormDTO) {
		this.firstName = userFormDTO.getFirstName();
		this.lastName = userFormDTO.getLastName();
		this.Sex = userFormDTO.getSex();
		this.cpf = userFormDTO.getCpf();
		this.birthDate = userFormDTO.getBirthdate();
		this.email = userFormDTO.getEmail();
		this.password = new BCryptPasswordEncoder().encode(userFormDTO.getPassword());
		this.active = userFormDTO.isActive();
	}
}
