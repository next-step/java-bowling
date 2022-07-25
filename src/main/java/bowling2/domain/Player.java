package bowling2.domain;

import bowling.exception.BowlingException;

import static bowling.exception.BowlingExceptionCode.INVALID_PLAYER_NAME;
import static bowling.exception.BowlingExceptionCode.INVALID_PLAYER_NAME_LENGTH;

public class Player {
    private final String name;

    public Player(String name) {
        if (empty(name)) {
            throw new BowlingException(INVALID_PLAYER_NAME, name);
        }
        if (invalidatedName(name)) {
            throw new BowlingException(INVALID_PLAYER_NAME_LENGTH, name);
        }
        this.name = name;
    }

    private boolean invalidatedName(String name) {
        return name.length() > 3;
    }

    private boolean empty(String name) {
        return name == null || name.isBlank();
    }

    public String name() {
        return name;
    }
}
