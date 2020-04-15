package bowling.domain;

import bowling.exception.BowlingException;
import bowling.exception.ExceptionType;
import lombok.Getter;

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

    public void play(int clearPinCount) {
        this.frames.play(clearPinCount);
    }

    public int getLastFrameNumber() {
        return this.frames.getFrameNumber();
    }

    public boolean isEnd() {
        return this.frames.isEnd();
    }

    private void validPlayerName(String playerName) {
        if (playerName.length() != PLAYER_NAME_LENGTH) {
            throw new BowlingException(ExceptionType.INVALID_NAME_LENGTH);
        }
    }
}
