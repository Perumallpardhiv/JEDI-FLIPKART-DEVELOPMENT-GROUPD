package com.flipkart.dao;

import java.sql.*;

/**
 * Utility class for establishing database connection.
 */
public class GetConnection {

    /**
     * Returns a connection to the FlipFit database.
     * 
     * @return Connection object or null if connection fails.
     */
    public static Connection getConnection() {
        try {
            // Load and establish connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/FlipFit", "root", "m@owani3pillu"
            );
        } catch (Exception e) {
            // Handle errors
            System.out.println(e);
            return null;
        }
    }
}
