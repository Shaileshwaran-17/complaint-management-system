package com.complaint;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {

    private static final String SERVER_URL =
            "jdbc:mysql://localhost:3306/?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

    private static final String DATABASE_URL =
            "jdbc:mysql://localhost:3306/complaint_management?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

    private static final String USER = "root";

    private static final String PASSWORD = System.getenv("MYSQL_PASSWORD");

    // Creates the database and complaints table
    public static void initializeDatabase() {

        try {
            // Connect to MySQL server
            Connection connection = DriverManager.getConnection(
                    SERVER_URL, USER, PASSWORD);

            Statement statement = connection.createStatement();

            // Create database
            statement.executeUpdate(
                    "CREATE DATABASE IF NOT EXISTS complaint_management");

            System.out.println("Database ready.");

            // Close the first connection
            statement.close();
            connection.close();

            // Connect to the complaint_management database
            connection = DriverManager.getConnection(
                    DATABASE_URL, USER, PASSWORD);

            statement = connection.createStatement();

            // Create complaints table
            String createTable = """
                    CREATE TABLE IF NOT EXISTS complaints (
                        complaint_id INT PRIMARY KEY AUTO_INCREMENT,
                        customer_name VARCHAR(100) NOT NULL,
                        email VARCHAR(100),
                        complaint_type VARCHAR(100),
                        description VARCHAR(500),
                        status VARCHAR(50) DEFAULT 'Pending',
                        complaint_date DATE
                    )
                    """;

            statement.executeUpdate(createTable);

            System.out.println("Complaints table ready.");

            statement.close();
            connection.close();

            System.out.println("Database setup completed successfully!");

        } catch (SQLException e) {

            System.out.println("Database setup failed!");
            e.printStackTrace();
        }
    }

    // Returns a connection to the complaint database
    public static Connection getConnection() throws SQLException {

        return DriverManager.getConnection(
                DATABASE_URL, USER, PASSWORD);
    }

    // Test the database connection
    public static void main(String[] args) {

        initializeDatabase();

        try {
            Connection connection = getConnection();

            System.out.println("Java successfully connected to MySQL!");

            connection.close();

        } catch (SQLException e) {

            System.out.println("Connection failed!");
            e.printStackTrace();
        }
    }
}