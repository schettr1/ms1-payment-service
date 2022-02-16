package com.sbc.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbc.entity.Payment;
import com.sbc.exception.InvalidRequestException;
import com.sbc.repository.PaymentRepository;

@Service
@Transactional
public class PaymentService {

	@Autowired
	PaymentRepository pay_repository;
	
	public void makePayment(Payment payment) {
		pay_repository.save(payment);		
	}

	public Payment getPayment(int id) {
		return pay_repository.findById(id)
				.orElseThrow(
						() -> new InvalidRequestException("id=" + id + " cannot be found."));
	}
	
}
