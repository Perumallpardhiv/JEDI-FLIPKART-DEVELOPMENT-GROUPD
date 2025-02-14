package com.flipkart.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {
    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection != null)
            return connection;
        else {
            try {
                // Database URL, Username, and Password
                String url = "jdbc:mysql://localhost:3306/FlipFit";
                String user = "root";
                String password = "root1234";

                // Establishing the Connection
                connection = DriverManager.getConnection(url, user, password);
                System.out.println("Database Connection Successful!");
            } catch (SQLException e) {
                System.err.println("Database Connection Failed!");
                e.printStackTrace();
            }
            return connection;
        }
    }

    public static void main(String[] args) {
        Connection conn = DBUtils.getConnection();
        if (conn != null) {
            System.out.println("Connection Established Successfully!");
        } else {
            System.out.println("Failed to Establish Connection.");
        }
    }
}
