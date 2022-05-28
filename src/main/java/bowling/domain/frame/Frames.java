package bowling.domain.frame;

import java.util.ArrayList;
import java.util.List;

public class Frames {

    private static final int ZERO = 0;
    private static final int ONE = 1;

    private final List<Frame> frames;

    private Frames(List<Frame> frames) {
        this.frames = frames;
    }

    public static Frames init() {
        return new Frames(new ArrayList<>());
    }

    public boolean isNext() {
        return this.frames.isEmpty()
                || !this.currentFrame().isFinalFrame()
                || (this.currentFrame().isFinalFrame() && !this.currentFrame().isFinishBowling());
    }

    public void bowling(int count) {
        if (this.frames.isEmpty()) {
            Frame newFrame = NormalFrame.bowling(ONE, count);
            this.frames.add(newFrame);
            return;
        }

        this.nextBowling(count);
    }

    private void nextBowling(int count) {
        Frame currentFrame = this.currentFrame();

        if (!currentFrame.isFinalFrame() && currentFrame.isFinishBowling()) {
            Frame nextFrame = currentFrame.next(count);
            this.frames.add(nextFrame);
            return;
        }

        currentFrame.bowling(count);
    }

    public int currentRound() {
        if (this.frames.isEmpty()) {
            return ONE;
        }

        Frame currentFrame = this.currentFrame();

        if (currentFrame.isFinishBowling() || currentFrame.isFinalFrame()) {
            return currentFrame.round() + ONE;
        }

        return currentFrame.round();
    }

    public Frame getFrame(int index) {
        return this.frames.get(index);
    }

    public int lastRound() {
        int lastRound = Math.subtractExact(this.frames.size(), ONE);

        return Math.max(lastRound, ZERO);
    }

    private Frame currentFrame() {
        return this.getFrame(this.lastRound());
    }
}
