package com.shopflow;

public class TestPlaceOrder {

    public static void main(String[] args) {

        Customer c = new Customer(1, "Rahul", "rahul@gmail.com");

        Product p = new Product(101, "Laptop", 50000, 8);

        Order O = new Order(3, c, p, 10);

        O.addItem(new OrderItem(p, 2));

        Payment payment = new Payment(102, O.getTotalPrice(), O.getTotalPrice());

        O.setPayment(payment);

        OrderDAO dao = new OrderDAO();
        dao.placeOrder(O);
    }
}