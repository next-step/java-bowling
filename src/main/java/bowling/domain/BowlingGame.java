package bowling.domain;

import bowling.dto.ScoreBoard;

public class BowlingGame {

    private final Player player;

    public BowlingGame(String playerName) {
        player = new Player(playerName);
    }


    public ScoreBoard scoreBoard() {
        return new ScoreBoard(player);
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
