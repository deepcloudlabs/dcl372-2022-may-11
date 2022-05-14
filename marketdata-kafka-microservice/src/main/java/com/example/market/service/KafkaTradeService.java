package com.example.market.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.market.document.TradeDocument;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class KafkaTradeService {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
	@Autowired private ObjectMapper mapper;
	
	@EventListener
	public void listenEvent(TradeDocument tradeDocument) throws JsonProcessingException {
		kafkaTemplate.send("trades", mapper.writeValueAsString(tradeDocument));
	}
}
