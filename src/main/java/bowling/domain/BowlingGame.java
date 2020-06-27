package bowling.domain;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.player.Player;

public class BowlingGame {

    private Frames frames;

    public BowlingGame(String playerName) {
        Player player = new Player(playerName);
        this.frames = new Frames(player);
    }

    public void addNextFrame() {
        frames.createNextFrame();
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

    public Frame findCurrentFrame() {
        return frames.findCurrentFrame();
    }
}
