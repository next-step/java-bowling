package bowling.domain;

import bowling.exception.BowlingException;
import bowling.exception.ExceptionType;
import lombok.Getter;

public class Bowling {
    private static final int PLAYER_NAME_LENGTH = 3;

    @Getter
    private String playerName;
    @Getter
    private Frames frames;

    public Bowling(String playerName) {
        validPlayerName(playerName);

        this.playerName = playerName;
        this.frames = new Frames();
    }

    public void play(int clearPinCount) {
        this.frames.play(clearPinCount);
    }

    private void validPlayerName(String playerName) {
        if (playerName.length() != PLAYER_NAME_LENGTH) {
            throw new BowlingException(ExceptionType.INVALID_NAME_LENGTH);
        }
    }

    public int getLastFrameNumber() {
        return this.frames.getFrameNumber();
    }

    public boolean isEnd() {
        return this.frames.isEnd();
    }
}
