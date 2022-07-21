package com.shopstyle.msbffshop.clients.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Media {

	private Long id;
	private String imagemUrl;
	private Sku sku;
}
