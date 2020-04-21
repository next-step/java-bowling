package seul.bowling.domain;

import lombok.Getter;
import seul.bowling.exception.BowlingException;
import seul.bowling.exception.ExceptionType;

public class Player {
    private static final int PLAYER_NAME_LENGTH = 3;

    @Getter
    private String name;
    @Getter
    private Frames frames;

    public Player(String name) {
        validPlayerName(name);

        this.name = name;
        this.frames = new Frames();
    }

    public Frame play(int clearPinCount) {
        return this.frames.play(clearPinCount);
    }

    public boolean isEnd() {
        return this.frames.end();
    }

    public boolean equalsName(String name) {
        return this.name.equals(name);
    }

    private void validPlayerName(String playerName) {
        if (playerName.length() != PLAYER_NAME_LENGTH) {
            throw new BowlingException(ExceptionType.INVALID_NAME_LENGTH);
        }
    }
}
