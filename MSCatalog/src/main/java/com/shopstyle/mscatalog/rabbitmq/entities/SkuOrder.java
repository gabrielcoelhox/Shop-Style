package com.shopstyle.mscatalog.rabbitmq.entities;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SkuOrder implements Serializable {
	private static final long serialVersionUID = 1L;

	private String orderId;
	private List<Sku> skus;
}
