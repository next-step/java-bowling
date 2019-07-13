package bowling.model;

import bowling.model.frame.FrameNumber;

import static bowling.model.frame.Frame.SEPARATOR_OF_FRAME;

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

    public void play(Pins pins) {
        gameEngine.play(pins);
    }

    public boolean isGameOver() {
        return gameEngine.isGameOver();
    }

    public FrameNumber getCurrentNumber() {
        return gameEngine.getCurrentNumber();
    }

    public String getCurrentStates() {
        return player.toString() + SEPARATOR_OF_FRAME + gameEngine.getCurrentStates();
    }
}
