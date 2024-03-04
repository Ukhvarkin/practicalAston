package ru.aston.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConfig {

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("PostgreSQL JDBC Driver не найден.");
        }
    }

    public Connection getConnection() throws SQLException {
        final String jdbcUrl = "jdbc:postgresql://localhost:5432/libraryaston";
        final String user = "root";
        final String password = "root";
        return DriverManager.getConnection(jdbcUrl, user, password);
    }
}