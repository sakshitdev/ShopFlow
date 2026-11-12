package com.shopflow;

import java.sql.Connection;
import java.sql.SQLException;

public class TestDBConnection {

    public static void main(String[] args) {

        try {
            Connection con = DBConnection.getConnection();

            System.out.println("MySQL Connected Successfully!");

            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
