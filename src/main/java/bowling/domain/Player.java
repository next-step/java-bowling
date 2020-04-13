package bowling.domain;

import bowling.exception.BowlingException;
import bowling.exception.ExceptionType;
import lombok.Getter;

public class Player {
    private static final int PLAYER_NAME_LENGTH = 3;

    @Getter
    private String name;

    public Player(String name) {
        validPlayerName(name);

        this.name = name;
    }

    private void validPlayerName(String playerName) {
        if (playerName.length() != PLAYER_NAME_LENGTH) {
            throw new BowlingException(ExceptionType.INVALID_NAME_LENGTH);
        }
    }
}
