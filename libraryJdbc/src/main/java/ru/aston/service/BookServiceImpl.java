package ru.aston.service;

import ru.aston.dao.BookDAO;
import ru.aston.jdbc.JdbcConfig;
import ru.aston.model.Book;

import java.util.List;

public class BookServiceImpl implements BookService {
    private final BookDAO bookDAO;

    public BookServiceImpl(JdbcConfig jdbcConfig) {
        this.bookDAO = new BookDAO(jdbcConfig);
    }

    @Override
    public void add(Book book) {
        bookDAO.add(book.getAuthor(), book.getTitle(), book.getAvailable(), book.getTotal());
    }

    @Override
    public Book getById(long bookId) {
        return bookDAO.getById(bookId);
    }

    @Override
    public List<Book> getAll() {
        return bookDAO.getAll();
    }

    @Override
    public void update(long bookId, Book book) {
        bookDAO.update(bookId, book.getAuthor(), book.getTitle(), book.getAvailable(), book.getTotal());
    }

    @Override
    public void delete(long bookId) {
        bookDAO.delete(bookId);
    }

    @Override
    public void rentBook(long userId, long bookId) {
        bookDAO.rentBook(userId, bookId);
    }
}
