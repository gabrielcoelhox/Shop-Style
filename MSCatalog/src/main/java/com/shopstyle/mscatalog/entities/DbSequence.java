package com.shopstyle.mscatalog.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "db_sequence")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DbSequence {
	
	@Id
	private String id;
	private Long seq;
}
