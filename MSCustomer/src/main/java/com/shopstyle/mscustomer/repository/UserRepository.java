package com.shopstyle.mscustomer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopstyle.mscustomer.entities.Usuario;

public interface UserRepository extends JpaRepository<Usuario, Long> {
	
	Optional<Usuario> findByEmail(String email);
}
