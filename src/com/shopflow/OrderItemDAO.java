package com.shopflow;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderItemDAO {

    public void saveOrderItems(Order O) {

        String sql = "INSERT INTO order_items (order_id, product_id, quantity, subtotal) VALUES (?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            for (OrderItem item : O.getItems()) {

                ps.setInt(1, O.getOrderId());
                ps.setInt(2, item.getProduct().getId());
                ps.setInt(3, item.getQuantity());
                ps.setDouble(4, item.getSubtotal());

                ps.executeUpdate();
            }

            System.out.println("Order items saved successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
