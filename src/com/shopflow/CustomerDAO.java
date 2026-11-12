package com.shopflow;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerDAO {

    public void saveCustomer(Customer c) {

        String sql = "INSERT INTO customers (customer_id, customer_name, email) VALUES (?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, c.getCustomerId());
            ps.setString(2, c.getName());
            ps.setString(3, c.getEmail());

            ps.executeUpdate();

            System.out.println("Customer saved successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}