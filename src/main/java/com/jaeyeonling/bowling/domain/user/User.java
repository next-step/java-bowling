package com.jaeyeonling.bowling.domain.user;

import com.jaeyeonling.bowling.domain.frame.BowlingType;
import com.jaeyeonling.bowling.utils.BowlingUtils;

public class User {

    private final Username username;

    private User(final Username username) {
        this.username = username;
    }

    public static User of(final String username) {
        return new User(Username.valueOf(username));
    }

    public Username getUsername() {
        return username;
    }

    public String format() {
        return BowlingType.DELIMITER + BowlingUtils.format(username.getUsername()) + BowlingType.DELIMITER;
    }
}
