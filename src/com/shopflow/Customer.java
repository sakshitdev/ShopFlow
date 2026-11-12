package com.shopflow;

public class Customer {
	
	private int customerId;
	private String name;
	private String email;
	public Customer(int customerId, String name, String email) {
		this.customerId = customerId;
		this.name = name;
		this.email = email;
	}
	
	
	
	public String getName() {
		return name;
	}
	
	public int getCustomerId() {
		return customerId;
	}
	
	public String getEmail() {
		return email;
	}



	public void displayDetails() {
		System.out.println("Customer ID: "+customerId);
		System.out.println("Customer Name: "+name);
		System.out.println("Customer Email: "+email);
	}
}
