package bowling.model;

import bowling.model.frame.FrameNumber;

public class BowlingGame {

    private Player player;
    private GameEngine gameEngine;

    private BowlingGame(Player player, GameEngine gameEngine) {
        this.player = player;
        this.gameEngine = gameEngine;
    }

    static BowlingGame settingOf(Player player) {
        return new BowlingGame(player, new GameEngine());
    }

    FrameNumber getCurrentNumber() {
        return gameEngine.getCurrentNumber();
    }

    public void play(DownPin downPin) {
        gameEngine.play(downPin);
    }

    boolean isYourTurn(FrameNumber currentNumber) {
        return !isGameOver() && getCurrentNumber().equals(currentNumber);
    }

    public Player getPlayer() {
        return player;
    }

    Board results() {
        return new Board(player, gameEngine.results());
    }

    boolean isGameOver() {
        return gameEngine.isGameOver();
    }
}