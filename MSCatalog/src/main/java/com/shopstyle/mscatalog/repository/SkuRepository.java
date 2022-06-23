package com.shopstyle.mscatalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopstyle.mscatalog.entities.Sku;

@Repository
public interface SkuRepository extends JpaRepository<Sku, Long> {

}
