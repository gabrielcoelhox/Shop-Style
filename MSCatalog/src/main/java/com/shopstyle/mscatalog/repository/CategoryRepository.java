package com.shopstyle.mscatalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopstyle.mscatalog.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
	
}
