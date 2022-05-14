package com.example.market.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

@Document(collection = "trades")
public class TradeDocument {
	@Id
	@JsonProperty("E")
	private Long eventId;
	@JsonProperty("s")
	private String symbol;
	@JsonProperty("p")
	private String price;
	@JsonProperty("q")
	private String quantity;
	@JsonProperty("b")
	private Long bidId;
	@JsonProperty("a")
	private Long askId;
	@JsonProperty("T")
	private Long timestamp;

	public TradeDocument() {
	}

	public Long getEventId() {
		return eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public Long getBidId() {
		return bidId;
	}

	public void setBidId(Long bidId) {
		this.bidId = bidId;
	}

	public Long getAskId() {
		return askId;
	}

	public void setAskId(Long askId) {
		this.askId = askId;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "TradeDocument [eventId=" + eventId + ", symbol=" + symbol + ", price=" + price + ", quantity="
				+ quantity + ", bidId=" + bidId + ", askId=" + askId + ", timestamp=" + timestamp + "]";
	}

}
