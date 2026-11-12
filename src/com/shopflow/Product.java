package com.shopflow;

public class Product {
	
	private int id;
	private String name;
	private double price;
	private int stockQuantity;
	
	public Product(int id, String name, double price, int stockQuantity) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.stockQuantity = stockQuantity;
	}
	
	public void displayDetails() {
		System.out.println("Product ID: "+id);
		System.out.println("Product Name: "+name);
		System.out.println("Product Price: "+price);
		System.out.println("Available Stock: "+stockQuantity);
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	public int getStockQuantity() {
		return stockQuantity;
	}
	
	public void reduceStock(int quantity) {
		if(quantity<=0) {
			throw new IllegalArgumentException("Quantity must be Positive...");
		}
		if(quantity>stockQuantity) {
			throw new IllegalArgumentException("Insufficient Stock...");
		}
		
		stockQuantity-=quantity;
	}
	
	public void restoreStock(int quantity) {
		if(quantity<=0) {
			throw new IllegalArgumentException("Quantity must be positive...");
		}
		
		stockQuantity+=quantity;
	}

}
