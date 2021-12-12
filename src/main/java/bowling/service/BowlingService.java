package bowling.service;

import bowling.domain.Board;
import bowling.domain.frame.Pin;
import bowling.domain.game.BowlingGame;

public class BowlingService {
    private BowlingGame bowlingGame;

    public BowlingService(BowlingGame bowlingGame) {
        this.bowlingGame = bowlingGame;
    }

    public Board bowl(Pin pin) {
        return new Board(bowlingGame.bowl(pin));
    }
}
