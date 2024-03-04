package ru.aston.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class User {
    private long id;
    private String name;
    private LocalDate birthday;
    private String role;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public User() {
    }

    public User(String name, String birthday, String role) {
        this.name = name;
        this.birthday = LocalDate.parse(birthday, formatter);
        this.role = role;
    }

    public User(long id, String name, LocalDate birthday, String role) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
