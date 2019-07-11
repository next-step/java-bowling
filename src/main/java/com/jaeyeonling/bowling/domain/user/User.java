package com.jaeyeonling.bowling.domain.user;

import com.jaeyeonling.bowling.domain.frame.BowlingSymbol;
import com.jaeyeonling.bowling.utils.BowlingUtils;
import com.jaeyeonling.bowling.view.StringVisualizable;

public class User implements StringVisualizable {

    private final Username username;

    private User(final Username username) {
        this.username = username;
    }

    public static User of(final String username) {
        return new User(Username.valueOf(username));
    }

    @Override
    public String visualize() {
        return BowlingSymbol.DELIMITER + BowlingUtils.format(username.visualize()) + BowlingSymbol.DELIMITER;
    }
}
