package com.shopstyle.mscatalog.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.shopstyle.mscatalog.entities.Category;

@Repository
public interface CategoryRepository extends MongoRepository<Category, Long> {
	
}
