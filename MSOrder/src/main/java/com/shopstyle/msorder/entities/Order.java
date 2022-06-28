package com.shopstyle.msorder.entities;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shopstyle.msorder.enums.Status;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document
@Getter
@Setter
@NoArgsConstructor
public class Order {

	@Id
	private String id;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate date;
	private Status status;
	private Double total;

}
