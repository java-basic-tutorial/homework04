package com.softserveinc.task03;

import java.util.Objects;

public class User {
    private final String login;
    private String password;

    public User(String login, String password) {
        if (Objects.isNull(login) || login.isBlank()) {
            throw new IllegalArgumentException("Login should not be null or empty");
        }
        if (Objects.isNull(password) || password.isBlank()) {
            throw new IllegalArgumentException("Password should not be null or empty");
        }
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(login, user.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login);
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
