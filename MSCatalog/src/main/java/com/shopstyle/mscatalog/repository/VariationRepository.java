package com.shopstyle.mscatalog.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.shopstyle.mscatalog.entities.Variation;

@Repository
public interface VariationRepository extends MongoRepository<Variation, Long> {

}
