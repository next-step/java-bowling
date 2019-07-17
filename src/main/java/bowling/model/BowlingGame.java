package bowling.model;

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

    public void play(Pin pin) {
        gameEngine.play(pin);
    }

    public Board results() {
        return new Board(player, gameEngine.results());
    }

    public boolean isGameOver() {
        return gameEngine.isGameOver();
    }
}
