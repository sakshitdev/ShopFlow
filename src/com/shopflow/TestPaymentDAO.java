package com.shopflow;

public class TestPaymentDAO {

    public static void main(String[] args) {

        Customer c = new Customer(1, "Rahul", "rahul@gmail.com");

        Product p = new Product(101, "Laptop", 50000, 10);

        Order O = new Order(1, c, p, 2);

        Payment payment = new Payment(101, 100000, O.getTotalPrice());

        O.setPayment(payment);

        PaymentDAO dao = new PaymentDAO();

        dao.savePayment(O);
    }
}