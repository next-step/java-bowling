package bowling.domain;

import bowling.dto.BowlingGameRequest;
import bowling.dto.ScoreBoard;

public class BowlingGame {

    private static final int TOTAL_NUMBER_OF_FRAME = 10;

    private final Players players;

    public BowlingGame(BowlingGameRequest bowlingGameRequest) {
        players = new Players(bowlingGameRequest.playerNames(), TOTAL_NUMBER_OF_FRAME);
    }

    public ScoreBoard scoreBoard() {
        return new ScoreBoard(players, TOTAL_NUMBER_OF_FRAME);
    }

    public boolean isDone() {
        return players.isDone();
    }

    public void play(int pintCount) {
        players.play(pintCount);
    }

    public String playingPlayer() {
        return players.playingPlayer().name();
    }
}
