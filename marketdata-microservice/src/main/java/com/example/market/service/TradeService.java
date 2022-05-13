package com.example.market.service;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import com.example.market.document.TradeDocument;
import com.example.market.repository.TradeRepository;

@Service
public class TradeService {
	private final TradeRepository tradeRepository;

	public TradeService(TradeRepository tradeRepository) {
		this.tradeRepository = tradeRepository;
	}
	
	@EventListener
	public void listenEvent(TradeDocument tradeDocument) {
		tradeRepository.save(tradeDocument);
	}
}
