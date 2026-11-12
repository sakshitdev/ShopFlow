package com.shopflow;

public class TestOrderItemDAO {

    public static void main(String[] args) {

        Customer c = new Customer(1, "Rahul", "rahul@gmail.com");

        Product p = new Product(101, "Laptop", 50000, 10);

        Order O = new Order(1, c, p, 2);

        O.addItem(new OrderItem(p, 2));

        OrderItemDAO dao = new OrderItemDAO();

        dao.saveOrderItems(O);
    }
}
