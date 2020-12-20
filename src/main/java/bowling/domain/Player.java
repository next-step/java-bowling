package bowling.domain;

import java.util.Objects;

public class Player {

    public static final int NAME_LENGTH = 3;
    private final String name;

    public Player(String name) {
        if (name.length() != NAME_LENGTH) {
            throw new IllegalArgumentException("Name size must be 3");
        }

        this.name = name;
    }
}
