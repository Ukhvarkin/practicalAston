package ru.aston.service;

import ru.aston.dao.UserDAO;
import ru.aston.jdbc.JdbcConfig;
import ru.aston.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    public UserServiceImpl(JdbcConfig jdbcConfig) {
        this.userDAO = new UserDAO(jdbcConfig);
    }

    @Override
    public void add(User user) {
        userDAO.add(user.getName(), user.getBirthday(), user.getRole());
    }

    @Override
    public User getById(long userId){
        return userDAO.getById(userId);
    }

    @Override
    public List<User> getAll(){
        return userDAO.getAll();
    }

    @Override
    public void update(long userId, User user) {
        userDAO.update(userId, user.getName(), user.getBirthday(), user.getRole());
    }

    @Override
    public void delete(long userId) {
        userDAO.delete(userId);
    }
}
