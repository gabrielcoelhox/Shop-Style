package com.shopstyle.mspayment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopstyle.mspayment.entities.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long>{

}
