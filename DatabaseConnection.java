package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String url = "jdbc:mysql://localhost:3306/login_db"; 
    private static final String user = "root"; 
    private static final String password = "Atish@123"; 

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("✅ Connected to MySQL database!");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("❌ Connection error: " + e.getMessage());
        }
        return connection;
    }

    // For testing
    public static void main(String[] args) {
        getConnection();
    }
}
