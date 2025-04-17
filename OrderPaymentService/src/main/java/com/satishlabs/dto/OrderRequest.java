package com.satishlabs.dto;

public class OrderRequest {
	private String productName;
	private int quantity;
	
	public OrderRequest() {}
	
	public OrderRequest(String productName, int quantity) {
		super();
		this.productName = productName;
		this.quantity = quantity;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "OrderRequest [productName=" + productName + ", quantity=" + quantity + "]";
	}
	
	
}
