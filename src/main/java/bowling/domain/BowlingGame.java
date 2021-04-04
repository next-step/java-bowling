package bowling.domain;

import bowling.dto.ScoreBoard;

public class BowlingGame {

    private final Player player;

    private static final int TOTAL_NUMBER_OF_FRAME = 10;

    public BowlingGame(String playerName) {
        player = new Player(playerName, TOTAL_NUMBER_OF_FRAME);
    }

    public ScoreBoard scoreBoard() {
        return new ScoreBoard(player, TOTAL_NUMBER_OF_FRAME);
    }

    public boolean isDone() {
        return player.isDone();
    }

    public int currentFrameNumber() {
        return player.currentFrameNumber();
    }

    public void play(int pintCount) {
        player.addPinCounts(pintCount);
    }
}
