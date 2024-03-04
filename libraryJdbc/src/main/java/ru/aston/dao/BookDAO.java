package ru.aston.dao;

import ru.aston.jdbc.JdbcConfig;
import ru.aston.model.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    private final JdbcConfig jdbc;

    public BookDAO(JdbcConfig jdbc) {
        this.jdbc = jdbc;
    }

    public void add(String author, String title, int available, int total) {
        String insertQuery = "INSERT INTO books (title, author, available, total) VALUES (?,?,?,?)";

        try (Connection connection = jdbc.getConnection();
             PreparedStatement ps = connection.prepareStatement(insertQuery)) {

            ps.setString(1, title);
            ps.setString(2, author);
            ps.setInt(3, available);
            ps.setInt(4, total);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Book getById(long bookId) {
        String sql = "SELECT * FROM books WHERE id = ?";

        try (Connection connection = jdbc.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, bookId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Book(
                            rs.getLong("id"),
                            rs.getString("title"),
                            rs.getString("author"),
                            rs.getInt("available"),
                            rs.getInt("total")
                    );
                } else {
                    throw new RuntimeException("Книга с id = " + bookId + " не найдена.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Книга с id = " + bookId + " не найдена.");
        }
    }

    public List<Book> getAll() {
        String sql = "SELECT * FROM books";
        List<Book> books = new ArrayList<>();

        try (Connection connection = jdbc.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getLong("id"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setAvailable(rs.getInt("available"));
                book.setTotal(rs.getInt("total"));
                books.add(book);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return books;
    }

    public void update(long bookId, String author, String title, int available, int total) {
        String updateQuery = "UPDATE books SET title = ?, author = ?, available = ?, total = ? WHERE id = ?";
        try (Connection connection = jdbc.getConnection();
             PreparedStatement ps = connection.prepareStatement(updateQuery)) {
            ps.setString(1, title);
            ps.setString(2, author);
            ps.setInt(3, available);
            ps.setInt(4, total);
            ps.setLong(5, bookId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(long bookId) {
        String sql = "DELETE FROM books WHERE id = ?";
        try (Connection connection = jdbc.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, bookId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Ползователь с id = " + bookId + "не найден");
        }
    }

    public void rentBook(long userId, long bookId) {
        String insertQuery = "INSERT INTO user_books (user_id, book_id) VALUES (?,?)";
        try (Connection connection = jdbc.getConnection();
             PreparedStatement ps = connection.prepareStatement(insertQuery)) {
            ps.setLong(1, userId);
            ps.setLong(2, bookId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
