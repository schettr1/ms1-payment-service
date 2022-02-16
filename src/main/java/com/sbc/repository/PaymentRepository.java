package com.sbc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sbc.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

}
