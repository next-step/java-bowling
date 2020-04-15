package bowling.domain;

import bowling.exception.BowlingException;

public class Player {

    private static final String PLAYER_NAME_LENGTH_ERR_MESSAGE = "이름은 3글자 이하여야 합니다.";

    private final String name;

    public Player(final String name) {
        validateNameLength(name);
        this.name = name;
    }

    private void validateNameLength(final String name) {
        if (name.length() > 3) {
            throw new BowlingException(PLAYER_NAME_LENGTH_ERR_MESSAGE);
        }
    }
}
