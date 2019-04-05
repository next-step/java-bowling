package domain;

import domain.frame.Frame;
import domain.frame.Frames;
import domain.frame.NormalFrame;

import static domain.frame.Frames.LAST_FRAME;
import static domain.frame.Frames.START_FRAME;

public class BowlingGame {
    private final PlayerName playerName;
    private final Frames frames = new Frames();

    public BowlingGame(PlayerName playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName.getName();
    }

    public Frames getFrames() {
        return frames;
    }

    public String getFramesStatus() {
        return frames.getStatus();
    }

    public boolean isFramesEmpty() {
        return frames.isEmpty();
    }

    public int getRecentFrameNumber() {
        return (frames.isEmpty()) ? START_FRAME : frames.getRecentFrameNumber();
    }

    public Frame play(int pin) {
        Frame currentFrame = (frames.isEmpty()) ? new NormalFrame(START_FRAME) : frames.getRecentFrame();

        if(!frames.contains(currentFrame)) {
            frames.add(currentFrame);
        }

        return currentFrame.bowl(pin);
    }

    public boolean isContinuable() {
        return frames.size() <= LAST_FRAME || !frames.getRecentFrame().isFinished();
    }
}