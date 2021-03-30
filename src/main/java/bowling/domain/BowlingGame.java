package bowling.domain;

import bowling.dto.ScoreBoard;

public class BowlingGame {

    private int currentFrameNumber;

    private static final int LAST_FRAME = 10;

    private String playerName;

    public BowlingGame(String playerName) {
        this.playerName = playerName;
    }


    public ScoreBoard scoreBoard() {
        return null;
    }

    public boolean isDone() {
        return currentFrameNumber == 10;
    }

    public int currentFrameNumber() {
        return currentFrameNumber;
    }

    public void play(String pintCounts) {

    }
}
