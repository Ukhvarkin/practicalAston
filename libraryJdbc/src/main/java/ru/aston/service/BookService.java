package ru.aston.service;

import ru.aston.model.Book;

import java.util.List;

public interface BookService {
    void add(Book book);
    Book getById(long bookId);
    List<Book> getAll();
    void update(long bookId, Book book);
    void delete(long bookId);
    void rentBook(long userId, long bookId);
}
