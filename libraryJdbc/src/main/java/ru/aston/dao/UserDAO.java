package ru.aston.dao;

import ru.aston.jdbc.JdbcConfig;
import ru.aston.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private final JdbcConfig jdbc;

    public UserDAO(JdbcConfig jdbc) {
        this.jdbc = jdbc;
    }

    public void add(String name, LocalDate birthday, String role) {
        String insertQuery = "INSERT INTO users (name, birthday, role) VALUES (?,?,?)";

        try (Connection connection = jdbc.getConnection();
             PreparedStatement ps = connection.prepareStatement(insertQuery)) {

            ps.setString(1, name);
            ps.setDate(2, java.sql.Date.valueOf(birthday));
            ps.setString(3, role);

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User getById(long userId) {
        String sql = "SELECT * FROM users WHERE id = ?";

        try (Connection connection = jdbc.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setLong(1, userId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new User(
                            rs.getLong("id"),
                            rs.getString("name"),
                            rs.getTimestamp("birthday").toLocalDateTime().toLocalDate(),
                            rs.getString("role")
                    );
                } else {
                    throw new RuntimeException("Ползователь с id = " + userId + " не найден.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ползователь с id = " + userId + "не найден");
        }
    }

    public List<User> getAll() {
        String sql = "SELECT * FROM users";
        List<User> users = new ArrayList<>();

        try (Connection connection = jdbc.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setBirthday(rs.getTimestamp("birthday").toLocalDateTime().toLocalDate());
                user.setRole("role");
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public void update(long userId, String name, LocalDate birthday, String role) {
        String updateQuery = "UPDATE users SET name = ?, birthday  = ?, role = ? WHERE id = ?";
        try (Connection connection = jdbc.getConnection();
             PreparedStatement ps = connection.prepareStatement(updateQuery)) {

            ps.setString(1, name);
            ps.setDate(2, java.sql.Date.valueOf(birthday));
            ps.setString(3, role);
            ps.setLong(4, userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(long userId) {
        String sql = "DELETE FROM users WHERE id = ?";
        try (Connection connection = jdbc.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setLong(1, userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Ползователь с id = " + userId + "не найден");
        }
    }
}
