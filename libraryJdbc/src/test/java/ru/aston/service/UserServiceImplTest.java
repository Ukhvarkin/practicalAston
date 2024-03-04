package ru.aston.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.aston.jdbc.JdbcConfig;
import ru.aston.model.User;
import ru.aston.utils.DatabaseUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserServiceImplTest {
    private final JdbcConfig jdbcConfig = new JdbcConfig();
    private final UserService userService = new UserServiceImpl(jdbcConfig);

    @AfterEach
    void afterEach() {
        DatabaseUtils.clearUserDatabase();
        DatabaseUtils.resetUserIdCounter();
    }

    @Test
    @DisplayName("Тест на добавление пользователя.")
    void add() {
        User user1 = new User("User1", "11.12.2005", "STUDENT");
        User user2 = new User("User2", "08.08.1980", "TEACHER");
        user1.setId(1);
        user2.setId(2);

        userService.add(user1);
        userService.add(user2);
        User user = userService.getById(2);

        assertEquals(user2.getId(), user.getId(), "id не сопадают!");
        assertEquals(user2.getName(), user.getName(), "Имена не сопадают!");
        assertEquals(user2.getBirthday(), user.getBirthday(), "Возраст не сопадает!");
        assertEquals(user2.getRole(), user.getRole(), "Роли не совпадают!");
    }

    @Test
    @DisplayName("Тест на получение пользователя по id.")
    void getById() {
        User user1 = new User("User1", "11.12.2005", "STUDENT");
        user1.setId(1);

        userService.add(user1);
        User user = userService.getById(1);

        assertEquals(user1.getId(), user.getId(), "id не сопадают!");
        assertEquals(user1.getName(), user.getName(), "Имена не сопадают!");
        assertEquals(user1.getBirthday(), user.getBirthday(), "Возраст не сопадает!");
        assertEquals(user1.getRole(), user.getRole(), "Роли не совпадают!");
    }

    @Test
    @DisplayName("Тест на получения списка всех пользователей.")
    void getAll() {
        User user1 = new User("User1", "11.12.2005", "STUDENT");
        User user2 = new User("User2", "08.08.1980", "TEACHER");
        userService.add(user1);
        userService.add(user2);

        assertEquals(2, userService.getAll().size());
    }

    @Test
    @DisplayName("Тест на редактирование данных пользователя.")
    void update() {
        User user1 = new User("User1", "11.12.2005", "STUDENT");
        User updatedUser = new User("User Update", "08.08.1980", "TEACHER");

        userService.add(user1);
        userService.update(1, updatedUser);

        User user = userService.getById(1);
        assertEquals(updatedUser.getName(), user.getName(), "Имена не сопадают!");
        assertEquals(updatedUser.getBirthday(), user.getBirthday(), "Возраст не сопадает!");
        assertEquals(updatedUser.getRole(), user.getRole(), "Роли не совпадают!");
    }

    @Test
    @DisplayName("Тест на удаление пользователя по id.")
    void delete() {
        User user1 = new User("User1", "11.12.2005", "STUDENT");
        userService.add(user1);
        userService.delete(1);

        assertThrows(RuntimeException.class, () -> userService.getById(1));
    }
}