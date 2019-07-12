package com.jaeyeonling.bowling.domain.user;

public class Username {

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

        return new Username(username);
    }

    public String getUsername() {
        return username;
    }
}