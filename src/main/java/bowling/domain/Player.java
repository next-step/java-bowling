package bowling.domain;

import bowling.exception.BowlingException;

import static bowling.exception.BowlingExceptionCode.INVALID_PLAYER_NAME;

public class Player {
    public Player(String name) {
        if (empty(name)) {
            throw new BowlingException(INVALID_PLAYER_NAME, name);
        }
    }

    private boolean empty(String name) {
        return name == null || name.isBlank();
    }
}
