package com.jaeyeonling.bowling.domain.user;

import com.jaeyeonling.bowling.view.StringVisualizable;

public class Username implements StringVisualizable {

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

    @Override
    public String visualize() {
        return username;
    }
}
