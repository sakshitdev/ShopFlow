package com.shopflow;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProductDAO {

    public void saveProduct(Product p) {

        String sql = "INSERT INTO products (product_id, product_name, price, stock_quantity) VALUES (?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, p.getId());
            ps.setString(2, p.getName());
            ps.setDouble(3, p.getPrice());
            ps.setInt(4, p.getStockQuantity());

            ps.executeUpdate();

            System.out.println("Product saved successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
