package com.softserveinc.task03;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class UserManager {

    private final Set<User> users;

    public UserManager() {
        users = new HashSet<>();
    }

    public void register(String login, String password) {
        users.add(new User(login, password));
    }

    public void delete(String login) {
        users.stream()
                .filter(user -> user.getLogin().equals(login))
                .findFirst()
                .ifPresent(users::remove);
    }

    public boolean exists(String login, String password) {
        return users.stream().anyMatch(u -> u.getLogin().equals(login) && u.getPassword().equals(password));
    }

    public boolean changePassword(String login, String oldPassword, String newPassword) {
        Optional<User> optionalUser = users.stream().filter(u -> u.getLogin().equals(login)).findFirst();
        if (optionalUser.isEmpty()) {
            return false;
        }
        User user = optionalUser.get();
        if (user.getPassword().equals(oldPassword)) {
            user.setPassword(newPassword);
            return true;
        }
        return false;
    }
}
