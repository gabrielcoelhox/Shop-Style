package com.shopstyle.msorder.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@NotNull (message = "id field cannot be null")
	private Long id;
	
	@NotNull (message = "addressId field cannot be null")
	private Long addressId;
}
