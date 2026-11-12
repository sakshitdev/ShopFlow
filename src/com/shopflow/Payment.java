package com.shopflow;

public class Payment {
	private int paymentId;
	private double amount;
	
	public Payment(int paymentId, double amount, double orderTotal) {
		if(amount<=0) {
			throw new IllegalArgumentException("Payment amount must be Positive...");
		}
		if(amount!= orderTotal) {
			throw new IllegalArgumentException("Payment amount must match the Order Total...");
		}
		this.paymentId = paymentId;
		this.amount = amount;
	}

	public int getPaymentId() {
		return paymentId;
	}

	public double getAmount() {
		return amount;
	}
	
	public void displayDetails() {
		System.out.println("Payment ID: "+paymentId);
		System.out.println("Payment amount: "+amount);
	}
}
