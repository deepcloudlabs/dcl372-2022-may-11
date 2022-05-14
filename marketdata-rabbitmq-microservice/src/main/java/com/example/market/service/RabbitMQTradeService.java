package com.example.market.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import com.example.market.document.TradeDocument;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class RabbitMQTradeService {
    @Autowired
    private RabbitTemplate rabbitTemplate;
	@Autowired private ObjectMapper mapper;
	
	@EventListener
	public void listenEvent(TradeDocument tradeDocument) throws JsonProcessingException {
		rabbitTemplate.convertAndSend("trade-exchange", mapper.writeValueAsString(tradeDocument));
	}
}
