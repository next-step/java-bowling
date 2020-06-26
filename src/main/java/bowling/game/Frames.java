package bowling.game;

import java.util.LinkedList;

public class Frames {
    private final LinkedList<Frame> frames;

    public Frames() {
        this.frames = new LinkedList<>();
        this.frames.add(new NormalFrame(1));
    }

    public Frame getCurrentFrame() {
        return frames.getLast();
    }

    public void createNextFrame() {
        Frame nextFrame = getCurrentFrame().createNextFrame();

        frames.add(nextFrame);
    }

    public int bowlCurrentFrame(final int pinCount) {
        return getCurrentFrame().bowl(pinCount);
    }

    public boolean hasRemainChance() {
        return getCurrentFrame().hasRemainChance();
    }

    public boolean isEndAllFrames() {
        Frame frame = getCurrentFrame();

        return frame.isLastFrame() && !frame.hasRemainChance();
    }
}
