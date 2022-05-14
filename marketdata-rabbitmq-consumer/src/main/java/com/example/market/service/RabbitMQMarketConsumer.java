package com.example.market.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQMarketConsumer {

	@RabbitListener(queues = "trade-queue")
	public void listenTradeEvent(String event) {
		System.err.println("Event from RabbitMQ: "+event);
	}
}
