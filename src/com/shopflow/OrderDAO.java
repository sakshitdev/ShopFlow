package com.shopflow;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderDAO {

    public void saveOrder(Order O) {

        String sql = "INSERT INTO orders (order_id, customer_id, total_amount, order_status) VALUES (?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, O.getOrderId());
            ps.setInt(2, O.getCustomer().getCustomerId());
            ps.setDouble(3, O.getTotalPrice());
            ps.setString(4, O.getOrderStatus());

            ps.executeUpdate();

            System.out.println("Order saved successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void placeOrder(Order O) {

        String orderSQL = "INSERT INTO orders VALUES (?, ?, ?, ?)";
        String itemSQL = "INSERT INTO order_items (order_id, product_id, quantity, subtotal) VALUES (?, ?, ?, ?)";
        String stockSQL = "UPDATE products SET stock_quantity = stock_quantity - ? WHERE product_id = ? AND stock_quantity >= ?";
        String paymentSQL = "INSERT INTO payments VALUES (?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection()) {

            con.setAutoCommit(false);

            try {
                if (O.getPayment() == null || O.getItems().isEmpty()) {
                    throw new IllegalStateException("Order items and payment are required.");
                }

                // Save order
                try (PreparedStatement ps = con.prepareStatement(orderSQL)) {
                    ps.setInt(1, O.getOrderId());
                    ps.setInt(2, O.getCustomer().getCustomerId());
                    ps.setDouble(3, O.getTotalPrice());
                    ps.setString(4, "Confirmed");
                    ps.executeUpdate();
                }

                // Save items and update stock
                for (OrderItem item : O.getItems()) {

                    try (PreparedStatement ps = con.prepareStatement(stockSQL)) {
                        ps.setInt(1, item.getQuantity());
                        ps.setInt(2, item.getProduct().getId());
                        ps.setInt(3, item.getQuantity());

                        if (ps.executeUpdate() == 0) {
                            throw new SQLException("Insufficient stock or product not found.");
                        }
                    }

                    try (PreparedStatement ps = con.prepareStatement(itemSQL)) {
                        ps.setInt(1, O.getOrderId());
                        ps.setInt(2, item.getProduct().getId());
                        ps.setInt(3, item.getQuantity());
                        ps.setDouble(4, item.getSubtotal());
                        ps.executeUpdate();
                    }
                }

                // Save payment
                try (PreparedStatement ps = con.prepareStatement(paymentSQL)) {
                    ps.setInt(1, O.getPayment().getPaymentId());
                    ps.setInt(2, O.getOrderId());
                    ps.setDouble(3, O.getPayment().getAmount());
                    ps.setString(4, "Successful");
                    ps.executeUpdate();
                }

                con.commit();
                System.out.println("Order placed successfully!");

            } catch (Exception e) {
                con.rollback();
                System.out.println("Order failed. All database changes rolled back.");
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}