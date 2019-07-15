package bowling.model;

import bowling.io.Board;
import bowling.model.frame.FrameNumber;

public class BowlingGame {

    private Player player;
    private GameEngine gameEngine;

    private BowlingGame(Player player, GameEngine gameEngine) {
        this.player = player;
        this.gameEngine = gameEngine;
    }

    public static BowlingGame settingOf(Player player) {
        return new BowlingGame(player, new GameEngine());
    }

    public FrameNumber getCurrentNumber() {
        return gameEngine.getCurrentNumber();
    }

    public BowlingGame play(Pins pins) {
        gameEngine.play(pins);
        return this;
    }

    public Board results() {
        return new Board(player, gameEngine.results());
    }

    public boolean isGameOver() {
        return gameEngine.isGameOver();
    }
}
