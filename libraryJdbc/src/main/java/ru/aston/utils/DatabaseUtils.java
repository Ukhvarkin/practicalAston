package ru.aston.utils;

import ru.aston.jdbc.JdbcConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseUtils {
    private static final JdbcConfig jdbcConfig = new JdbcConfig();

    public static void clearUserDatabase() {
        try (Connection connection = jdbcConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM users")) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Ошибка при очистке базы данных", e);
        }
    }

    public static void resetUserIdCounter() {
        try (Connection connection = jdbcConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement("ALTER SEQUENCE users_id_seq RESTART WITH 1")) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Ошибка при сбросе счетчика id", e);
        }
    }

    public static void clearBookDatabase() {
        try (Connection connection = jdbcConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM books")) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Ошибка при очистке базы данных", e);
        }
    }

    public static void resetBookIdCounter() {
        try (Connection connection = jdbcConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement("ALTER SEQUENCE books_id_seq RESTART WITH 1")) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Ошибка при сбросе счетчика id", e);
        }
    }
}
