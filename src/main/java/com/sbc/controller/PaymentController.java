package com.sbc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.sbc.entity.Payment;
import com.sbc.service.PaymentService;

@RestController
@EnableBinding(Sink.class)
@RequestMapping(path="/payments")
public class PaymentController {

	@Autowired
	PaymentService pservice;
	
	// RabbitMQ message Consumer/Subscriber
	@StreamListener(target = Sink.INPUT)
	public void processRegistrationPayment(String stringPayment) {
		// print String (JSON) to console
		System.out.println("Registration-Payment String(JSON): " + stringPayment);
		// convert String (JSON) to JavaObject  
        Gson gson = new Gson();  
        Payment payment = gson.fromJson(stringPayment, Payment.class);  
		pservice.makePayment(payment);
		// print Java Object to console
		System.out.println("Registration-Payment (Java Object): " + payment.toString());
	}
	
	
	@PostMapping(path="/new", consumes="application/json")
	public void makePayment(@RequestBody Payment payment) {
		
		// call PaymentService
		pservice.makePayment(payment);

	}
	
	@GetMapping(path="/find/{id}", produces="application/json")
	public Payment getPayment(@PathVariable int id) {
		
		return pservice.getPayment(id);	
	}
}
