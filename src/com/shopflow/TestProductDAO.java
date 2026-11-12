package com.shopflow;

public class TestProductDAO {

    public static void main(String[] args) {

        Product p = new Product(101, "Laptop", 50000, 10);

        ProductDAO dao = new ProductDAO();

        dao.saveProduct(p);
    }
}