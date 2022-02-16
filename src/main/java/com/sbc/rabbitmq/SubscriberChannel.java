package com.sbc.rabbitmq;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * create Subscriber Channel for receiving messages using RabbitMQ
 * @author suny4 02/27/21
 *
 */
public interface SubscriberChannel {
	
	String PAYMENT = "employeePaymentChannel";

	@Input(PAYMENT)
	SubscribableChannel employeePayment();
}
