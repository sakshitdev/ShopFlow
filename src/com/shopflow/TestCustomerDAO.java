package com.shopflow;

public class TestCustomerDAO {

    public static void main(String[] args) {

        Customer c = new Customer(1, "Rahul", "rahul@gmail.com");

        CustomerDAO dao = new CustomerDAO();

        dao.saveCustomer(c);
    }
}