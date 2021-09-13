package bowling.domain;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.frame.NormalFrame;
import bowling.domain.player.Player;

import java.util.List;

public class BowlingGame {

    private final Player player;
    private final Frames frames;

    public BowlingGame(Player player, Frames frames) {
        this.player = player;
        this.frames = frames;
        frames.add(new NormalFrame(null));
    }

    public BowlingGame(String playerName) {
        this (new Player(playerName), new Frames());
    }

    public String playerName() {
        return player.name();
    }

    public List<Frame> frames() {
        return frames.list();
    }

    public int currentFrameNumber() {
        return currentFrame().number();
    }

    public Frame currentFrame() {
        return frames.currentFrame();
    }

    public boolean currentFrameIsEnd() {
        return currentFrame().isEnd();
    }

    public void nextFrame() {
        Frame nextFrame = currentFrame().next();
        frames.add(nextFrame);
    }

    public boolean isNotEnd() {
        return !frames.isEnd();
    }

    public void roll(int fallenPin) {
        if (isNotEnd() && currentFrameIsEnd()) {
            nextFrame();
        }
        frames.roll(currentFrameNumber(), fallenPin);
    }
}
