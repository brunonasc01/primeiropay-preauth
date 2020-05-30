package com.primeiropay.preauth.model;

public class Card {

	private String number;
	
	private String holder;
	
	private String expiryMonth;
	
	private String expiryYear;
	
	private String cvv;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getHolder() {
		return holder;
	}

	public void setHolder(String holder) {
		this.holder = holder;
	}

	public String getExpiryMonth() {
		return expiryMonth;
	}

	public void setExpiryMonth(String expiryMonth) {
		this.expiryMonth = expiryMonth;
	}

	public String getExpiryYear() {
		return expiryYear;
	}

	public void setExpiryYear(String expiryYear) {
		this.expiryYear = expiryYear;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}
	
	@Override
	public String toString() {
		return  "&card.number="+getNumber()
				+ "&card.holder="+getHolder()
				+ "&card.expiryMonth="+getExpiryMonth()
				+ "&card.expiryYear="+getExpiryYear()
				+ "&card.cvv="+getCvv();
	}
}
