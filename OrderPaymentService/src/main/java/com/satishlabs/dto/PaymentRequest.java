package com.satishlabs.dto;

public class PaymentRequest {
	private double pricePerUnit;
	private int quantity;
	
	public PaymentRequest() {}
	
	public PaymentRequest(double pricePerUnit, int quantity) {
		super();
		this.pricePerUnit = pricePerUnit;
		this.quantity = quantity;
	}

	public double getPricePerUnit() {
		return pricePerUnit;
	}

	public void setPricePerUnit(double pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "PaymentRequest [pricePerUnit=" + pricePerUnit + ", quantity=" + quantity + "]";
	}
	
	
}
