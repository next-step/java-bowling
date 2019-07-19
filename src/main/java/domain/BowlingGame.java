package domain;

import domain.frame.Frame;
import domain.frame.Frames;
import domain.frame.NormalFrame;

import java.util.Arrays;
import java.util.List;

public class BowlingGame {

    private Player player;
    private Frames frames;

    private BowlingGame(Player player) {
        this.player = player;
        this.frames = Frames.from(Arrays.asList(NormalFrame.initFrame()));
    }

    public static BowlingGame from(Player player) {
        return new BowlingGame(player);
    }

    public void play(Pins fallenPins) {
        frames.play(fallenPins);
    }

    public Frame currentFrame() {
        return frames.currentFrame();
    }

    public boolean isGameOver() {
        return frames.isGameOver();
    }

    public Player getPlayer() {
        return player;
    }

    public List<Frame> getFrames() {
        return frames.getFrames();
    }
}
