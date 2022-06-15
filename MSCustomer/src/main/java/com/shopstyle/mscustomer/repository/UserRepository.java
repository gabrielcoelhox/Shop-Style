package com.shopstyle.mscustomer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopstyle.mscustomer.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
