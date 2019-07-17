package com.jaeyeonling.bowling.domain.user;

import java.util.HashMap;
import java.util.Map;

public class Username {

    private static final Map<String, Username> CACHE = new HashMap<>();

    public static final int LENGTH = 3;

    static final String PATTERN = String.format("[A-Z]{%d}", LENGTH);

    private final String username;

    private Username(final String username) {
        this.username = username;
    }

    public static Username valueOf(final String username) {
        if (!username.matches(PATTERN)) {
            throw new InvalidUsernameException(username);
        }

        return CACHE.computeIfAbsent(username, Username::new);
    }

    public String getUsername() {
        return username;
    }
}