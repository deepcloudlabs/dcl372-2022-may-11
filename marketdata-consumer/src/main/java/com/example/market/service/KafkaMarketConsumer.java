package com.example.market.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaMarketConsumer {

	@KafkaListener(topics = "trades", groupId = "market-consumer")
	public void listenTradeEvent(String event) {
		System.err.println("Event from Kafka: "+event);
	}
}
