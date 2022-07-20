package com.shopstyle.msaudit.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.shopstyle.msaudit.entities.Order;

@Repository
public interface AuditRepository extends MongoRepository<Order, String>{

}
