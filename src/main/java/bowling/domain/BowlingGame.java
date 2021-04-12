package bowling.domain;

import bowling.dto.BowlingGameRequest;
import bowling.dto.ScoreBoard;

public class BowlingGame {

    private static final int TOTAL_NUMBER_OF_FRAME = 10;

    private final Player player;

    public BowlingGame(BowlingGameRequest bowlingGameRequest) {
        player = new Player(bowlingGameRequest.playerName(), TOTAL_NUMBER_OF_FRAME);
    }

    public ScoreBoard scoreBoard() {
        return new ScoreBoard(player, TOTAL_NUMBER_OF_FRAME);
    }

    public boolean isDone() {
        return player.isDone();
    }

    public int currentFrameNumber() {
        return player.currentFrameNumber().number();
    }

    public void play(int pintCount) {
        player.addPinCount(pintCount);
    }
}
