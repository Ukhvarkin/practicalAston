package ru.aston.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.aston.jdbc.JdbcConfig;
import ru.aston.model.Book;
import ru.aston.utils.DatabaseUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BookServiceImplTest {
    private final JdbcConfig jdbcConfig = new JdbcConfig();
    private final BookService bookService = new BookServiceImpl(jdbcConfig);

    @AfterEach
    void afterEach() {
        DatabaseUtils.clearBookDatabase();
        DatabaseUtils.resetBookIdCounter();
    }

    @Test
    @DisplayName("Тест на добавление книги.")
    void add() {
        Book book1 = new Book("book1", "author1", 5, 10);
        Book book2 = new Book("book2", "author2", 11, 11);
        book1.setId(1);
        book2.setId(2);

        bookService.add(book1);
        bookService.add(book2);
        Book book = bookService.getById(2);

        assertEquals(book2.getId(), book.getId(), "id не сопадают!");
        assertEquals(book2.getAuthor(), book.getAuthor(), "Имена не сопадают!");
        assertEquals(book2.getTitle(), book.getTitle(), "Названия не сопадают!");
        assertEquals(book2.getAvailable(), book.getAvailable(), "Количество не совпадает!");
        assertEquals(book2.getTotal(), book.getTotal(), "Количество не совпадает!");
    }

    @Test
    @DisplayName("Тест на получение книги по id.")
    void getById() {
        Book book1 = new Book("book1", "author1", 5, 10);
        book1.setId(1);

        bookService.add(book1);
        Book book = bookService.getById(1);

        assertEquals(book1.getId(), book.getId(), "id не сопадают!");
        assertEquals(book1.getAuthor(), book.getAuthor(), "Имена не сопадают!");
        assertEquals(book1.getTitle(), book.getTitle(), "Названия не сопадают!");
        assertEquals(book1.getAvailable(), book.getAvailable(), "Количество не совпадает!");
        assertEquals(book1.getTotal(), book.getTotal(), "Количество не совпадает!");
    }

    @Test
    @DisplayName("Тест на получения списка всех книг.")
    void getAll() {
        Book book1 = new Book("book1", "author1", 5, 10);
        Book book2 = new Book("book2", "author2", 11, 11);
        bookService.add(book1);
        bookService.add(book2);

        assertEquals(2, bookService.getAll().size());
    }

    @Test
    @DisplayName("Тест на редактирование данных пользователя.")
    void update() {
        Book book1 = new Book("book1", "author1", 5, 10);
        Book updatedBook = new Book("book2", "author2", 11, 11);

        bookService.add(book1);
        bookService.update(1, updatedBook);

        Book book = bookService.getById(1);
        assertEquals(updatedBook.getAuthor(), book.getAuthor(), "Имена не сопадают!");
        assertEquals(updatedBook.getTitle(), book.getTitle(), "Названия не сопадают!");
        assertEquals(updatedBook.getAvailable(), book.getAvailable(), "Количество не совпадает!");
        assertEquals(updatedBook.getTotal(), book.getTotal(), "Количество не совпадает!");
    }

    @Test
    @DisplayName("Тест на удаление пользователя по id.")
    void delete() {
        Book book1 = new Book("book1", "author1", 5, 10);
        bookService.add(book1);
        bookService.delete(1);

        assertThrows(RuntimeException.class, () -> bookService.getById(1));
    }
}