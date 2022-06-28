package com.shopstyle.mspayment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopstyle.mspayment.entities.Installment;

@Repository
public interface InstallmentRepository extends JpaRepository<Installment, Long>{

}
