package com.shopflow;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PaymentDAO {

    public void savePayment(Order O) {

        String sql = "INSERT INTO payments (payment_id, order_id, amount, payment_status) VALUES (?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, O.getPayment().getPaymentId());
            ps.setInt(2, O.getOrderId());
            ps.setDouble(3, O.getPayment().getAmount());
            ps.setString(4, "Successful");

            ps.executeUpdate();

            System.out.println("Payment saved successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}