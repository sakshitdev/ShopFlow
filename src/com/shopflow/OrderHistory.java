package com.shopflow;

import java.util.ArrayList;
import java.util.List;

public class OrderHistory {
	
	private List<Order> orders= new ArrayList<>();
	
	public void addOrder(Order o) {
		if(o==null) {
			throw new IllegalArgumentException("Order cannot be null.");
		}
		for(Order existingOrder: orders) {
			if(existingOrder.getOrderId() == o.getOrderId()) {
				throw new IllegalArgumentException("Duplicate Order ID.");
			}
		}
		orders.add(o);
	}
	
	public List<Order> getAllOrders(){
		return orders;
	}
	
	public Order findOrderById(int orderId) {
		for (Order o:  orders) {
			if(o.getOrderId() == orderId) {
				return o;
			}
		}
		
		return null;
	}
	
	public void displayAllOrders() {
		if(orders.isEmpty()) {
			System.out.println("No orders found.");
			return;
		}
		
		for(Order o: orders) {
			System.out.println("Order ID: "+o.getOrderId());
			System.out.println("---------");
			o.displayDetails();
			System.out.println();
		}
	}
}