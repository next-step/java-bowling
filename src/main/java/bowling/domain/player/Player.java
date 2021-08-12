package bowling.domain.player;

import bowling.exception.RangeArgumentException;

import java.util.Objects;

public class Player {
    private static final int LIMIT_LENGTH_OF_NAME = 3;

    private final String name;

    private Player(String name) {
        validate(name);
        this.name = name;
    }

    private void validate(String name) {
        if (isEmpty(name) || name.length() != LIMIT_LENGTH_OF_NAME) {
            throw RangeArgumentException.onlyEqualTo(LIMIT_LENGTH_OF_NAME);
        }
    }

    public static Player of(String name) {
        return new Player(name);
    }

    public String getName() {
        return name;
    }

    private boolean isEmpty(String value) {
        return Objects.isNull(value) || value.isEmpty();
    }
}
