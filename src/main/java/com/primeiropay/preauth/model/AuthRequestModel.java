package com.primeiropay.preauth.model;

import java.math.BigDecimal;

public class AuthRequestModel {
	
	private String entity_id;
	
	private BigDecimal amount;
	
	private String currency;
	
	private String paymentBrand;
	
	private String paymentType;
	
	private Card card;

	public String getEntity_id() {
		return entity_id;
	}

	public void setEntity_id(String entity_id) {
		this.entity_id = entity_id;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getPaymentBrand() {
		return paymentBrand;
	}

	public void setPaymentBrand(String paymentBrand) {
		this.paymentBrand = paymentBrand;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}
	
	@Override
	public String toString() {		
		return "entityId="+getEntity_id()
			+	"&amount="+getAmount()
			+	"&currency="+getCurrency()
			+	"&paymentBrand="+getPaymentBrand()
			+	"&paymentType="+getPaymentType()
			+	getCard().toString();
	}
}
