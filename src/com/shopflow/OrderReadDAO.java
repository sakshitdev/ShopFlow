package com.shopflow;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderReadDAO {

    public void displayOrder(int orderId) {

        String sql = "SELECT o.order_id, c.customer_name, o.total_amount, "
                + "o.order_status, p.product_name, oi.quantity, oi.subtotal, "
                + "pay.payment_id, pay.amount, pay.payment_status "
                + "FROM orders o "
                + "JOIN customers c ON o.customer_id = c.customer_id "
                + "JOIN order_items oi ON o.order_id = oi.order_id "
                + "JOIN products p ON oi.product_id = p.product_id "
                + "LEFT JOIN payments pay ON o.order_id = pay.order_id "
                + "WHERE o.order_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, orderId);

            try (ResultSet rs = ps.executeQuery()) {

                if (!rs.next()) {
                    System.out.println("Order not found.");
                    return;
                }

                do {
                    System.out.println("Order ID: " + rs.getInt("order_id"));
                    System.out.println("Customer: " + rs.getString("customer_name"));
                    System.out.println("Product: " + rs.getString("product_name"));
                    System.out.println("Quantity: " + rs.getInt("quantity"));
                    System.out.println("Subtotal: " + rs.getDouble("subtotal"));
                    System.out.println("Total Amount: " + rs.getDouble("total_amount"));
                    System.out.println("Order Status: " + rs.getString("order_status"));
                    System.out.println("Payment ID: " + rs.getInt("payment_id"));
                    System.out.println("Payment Amount: " + rs.getDouble("amount"));
                    System.out.println("Payment Status: " + rs.getString("payment_status"));
                    System.out.println("------------------------");

                } while (rs.next());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}