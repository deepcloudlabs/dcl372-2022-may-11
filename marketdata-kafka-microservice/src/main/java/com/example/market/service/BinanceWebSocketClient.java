package com.example.market.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.WebSocketClient;

import com.example.market.document.TradeDocument;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class BinanceWebSocketClient implements WebSocketHandler {
    @Autowired
    private WebSocketClient webSocketClient;
    @Autowired
    private ApplicationEventPublisher eventPublisher;
    @Autowired
    private ObjectMapper mapper;
    
	@PostConstruct
	public void connect() {
		webSocketClient.doHandshake(this, "wss://stream.binance.com:9443/ws/btcusdt@trade");
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.err.println("Connected to the binance ws server.");
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		var trade = message.getPayload().toString();
		var tradeDocument = mapper.readValue(trade, TradeDocument.class);
		eventPublisher.publishEvent(tradeDocument);
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable e) throws Exception {
		System.err.println("Error has occured: "+e.getMessage());
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		System.err.println("Disconnected from the binance ws server.");
	}

	@Override
	public boolean supportsPartialMessages() {
		return false;
	}
}
