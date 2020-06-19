package bowling.domain;

import bowling.domain.frame.Frames;
import bowling.domain.player.Player;

public class BowlingGame {

    private Frames frames;

    public BowlingGame(String playerName) {
        Player player = new Player(playerName);
        this.frames = new Frames(player);
    }

    public void addPoint(int point) {
        if (frames.gameOver()) {
            throw new IllegalArgumentException("bowlingGame is finish");
        }

        frames.addPoint(point);
    }

    public boolean gameOver() {
        return frames.gameOver();
    }

    public Frames getFrames() {
        return frames;
    }
}
