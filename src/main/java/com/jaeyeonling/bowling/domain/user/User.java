package com.jaeyeonling.bowling.domain.user;

import java.util.HashMap;
import java.util.Map;

public class User {

    private static final Map<Username, User> CACHE = new HashMap<>();

    private final Username username;

    private User(final Username username) {
        this.username = username;
    }

    public static User of(final String username) {
        return of(Username.valueOf(username));
    }

    public static User of(final Username username) {
        return CACHE.computeIfAbsent(username, User::new);
    }

    public Username getUsername() {
        return username;
    }
}
