package com.shopflow;

import java.util.ArrayList;
import java.util.List;

public class Order {
	
	private int orderId;
	private Customer customer;
	private Product product;
	private int quantity;
	private Payment payment;
	private boolean paymentConfirmed= false;
	private boolean cancelled= false;
	private List<OrderItem> items = new ArrayList<>();
	
	public Order(int orderId, Customer customer, Product product, int quantity) {
		
		if(customer==null) {
			throw new IllegalArgumentException("Customer cannot be null...");
		}
		
		if(product==null) {
			throw new IllegalArgumentException("Product cannot be null...");
		}
		
		if(quantity<=0) {
			throw new IllegalArgumentException("Order quantity must be positive...");
		}
		this.orderId = orderId;
		this.customer = customer;
		this.product = product;
		this.quantity = quantity;
	}
	
	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	
	public void confirmPayment() {
		if(payment== null) {
			throw new IllegalStateException("Payment has not been made...");
		}
		if(paymentConfirmed) {
			throw new IllegalStateException("Payment is already Confirmed...");
		}
		
		if(items.isEmpty()) {
			product.reduceStock(quantity);
		}else {
			for(OrderItem item : items) {
				if (item.getQuantity() > item.getProduct().getStockQuantity()) {
					throw new IllegalArgumentException("Insufficient stock for product: "+item.getProduct().getName());
				}
			}
			for (OrderItem item:items) {
				item.getProduct().reduceStock(item.getQuantity());
			}
		}
		paymentConfirmed= true;
	}
	
	public int getOrderId() {
		return orderId;
	}

	public void cancelOrder() {
		if(!paymentConfirmed) {
			throw new IllegalStateException("Cannot cancel an order that is not confirmed...");
		}
		if(cancelled) {
			throw new IllegalStateException("Order is already cancelled...");
		}
		
		if(items.isEmpty()) {
			product.restoreStock(quantity);
		}else {
			for(OrderItem item : items) {
				item.getProduct().restoreStock(item.getQuantity());
			}
		}
		cancelled= true;
	}



	public void displayDetails() {
		System.out.println("Order ID: "+orderId);
		System.out.println("Order Status: "+(cancelled ? "Cancelled" : (paymentConfirmed ? "Confirmed" : "Pending")));
		System.out.println("Customer: "+customer.getName());
		if(items.isEmpty()) {
			System.out.println("Product: "+product.getName());
			System.out.println("Quantity: "+quantity);
		}else {
			for(OrderItem item : items) {
				System.out.println("Product: "+item.getProduct().getName());
				System.out.println("Quantity: "+item.getQuantity());
				System.out.println("Subtotal: "+item.getSubtotal());
			}
		}
		
		System.out.println("Total Price: "+getTotalPrice());
		
		if(payment!= null) {
			payment.displayDetails();
		}
	}
	
	public void addItem(OrderItem item) {
		if(item == null) {
			throw new IllegalArgumentException("Order item cannot be null...");
		}
		if(paymentConfirmed) {
			throw new IllegalStateException("Cannot modify an order after payment confirmation...");
		}
		
		for(OrderItem existingItem : items) {
			if(existingItem.getProduct() == item.getProduct()) {
				throw new IllegalArgumentException("Product is already in the order...");
			}
		}
		
		items.add(item);
	}
	
	public double getTotalPrice() {
		if(items.isEmpty()) {
			return product.getPrice() * quantity;
		}
		
		double total = 0;
		
		for(OrderItem item : items) {
			total += item.getSubtotal();
		}
		return total;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	
	public List<OrderItem> getItems(){
		return items;
	}
	
	public Payment getPayment() {
		return payment;
	}
	
	public String getOrderStatus() {
		return cancelled ? "Cancelled" : (paymentConfirmed ? "Confirmed" : "Pending");
	}
}
