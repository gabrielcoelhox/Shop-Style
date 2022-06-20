package com.shopstyle.mscatalog.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.shopstyle.mscatalog.entities.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, Long>{

}
