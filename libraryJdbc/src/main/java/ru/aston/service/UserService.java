package ru.aston.service;

import ru.aston.model.User;

import java.text.ParseException;
import java.util.List;

public interface UserService {
    void add(User user);

    User getById(long userId);

    List<User> getAll();

    void update(long userId, User user);

    void delete(long userId);
}
