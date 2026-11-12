package com.shopflow;

public class Main {
	public static void main(String[] args) {
		Product p= new Product(1, "Laptop", 50000, 10);
		Product p2= new Product(2, "Mouse", 2000, 20);
		
		p.displayDetails();
		
		System.out.println("------");
		
		Customer c= new Customer(101, "Sakshit", "sakshit15@gmail.com");
		c.displayDetails();
		
		OrderHistory OH= new OrderHistory();
		
		System.out.println("------");
		
		try{
			Order o= new Order(1, c, p, 2);
			o.addItem(new OrderItem(p, 2));
			o.addItem(new OrderItem(p2, 1));
			OH.addOrder(o);
			
			double paymentAmount= o.getTotalPrice();
			Payment py= new Payment(101, paymentAmount, o.getTotalPrice());
			o.setPayment(py);
			o.confirmPayment();
		    
		    System.out.println("Updated Available Stocks: "+p.getStockQuantity());
		    System.out.println("Updated MOuse Stock: "+p2.getStockQuantity());
		    
		    o.cancelOrder();
		    System.out.println("Stock after Cancellation: "+p.getStockQuantity());
		    System.out.println("Mouse stock after cancellation: "+p2.getStockQuantity());
		    
		    o.displayDetails();
		    
		    System.out.println("\n==== Order History ====");
		    OH.displayAllOrders();
		    
		    System.out.println("\n==== Search Order ====");
		    Order foundOrder= OH.findOrderById(1);
		    
		    if(foundOrder != null) {
		    	foundOrder.displayDetails();
		    }else {
		    	System.out.println("Order not found.");
		    }
		    
	}catch(IllegalArgumentException | IllegalStateException e) {
		System.out.println("Order  Failed: "+e.getMessage());
	}
	}
}
