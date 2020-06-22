package bowling.domain;

import bowling.domain.frame.Frames;
import bowling.domain.player.Player;

public class BowlingGame {

    private Frames frames;

    public BowlingGame(String playerName) {
        Player player = new Player(playerName);
        this.frames = new Frames(player);
    }

    public void playGame(int point) {
        if (isGameOver()) {
            throw new IllegalArgumentException("bowlingGame is finish");
        }

        frames.addPoint(point);
    }

    public boolean isGameOver() {
        return frames.isGameOver();
    }

    public Frames getFrames() {
        return frames;
    }

    public int currentPlayFrameIndex() {
        return frames.getFrameSize();
    }
}
