package com.shopflow;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AllOrdersDAO {

    public void displayAllOrders() {

        String sql = "SELECT order_id, customer_id, total_amount, order_status FROM orders";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                System.out.println("Order ID: " + rs.getInt("order_id"));
                System.out.println("Customer ID: " + rs.getInt("customer_id"));
                System.out.println("Total Amount: " + rs.getDouble("total_amount"));
                System.out.println("Order Status: " + rs.getString("order_status"));
                System.out.println("------------------------");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
