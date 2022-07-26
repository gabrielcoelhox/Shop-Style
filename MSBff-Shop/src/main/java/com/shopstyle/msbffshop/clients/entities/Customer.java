package com.shopstyle.msbffshop.clients.entities;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String email;
	private String password;
}
