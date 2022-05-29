package bowling.domain.frame;

import java.util.ArrayList;
import java.util.List;

public class Frames {

    private static final int MINIMUM_ROUND = 0;
    private static final int FIRST_ROUND = 1;

    private final List<Frame> frames;

    private Frames(List<Frame> frames) {
        this.frames = frames;
    }

    public static Frames init() {
        return new Frames(new ArrayList<>());
    }

    public boolean isEmpty() {
        return this.frames.isEmpty();
    }

    public boolean isNext() {
        return this.isEmpty()
                || !this.currentFrame().isFinalFrame()
                || (this.currentFrame().isFinalFrame() && !this.currentFrame().isFinishBowling());
    }

    public void bowling(int pins) {
        if (this.isEmpty()) {
            Frame newFrame = NormalFrame.bowling(FIRST_ROUND, pins);
            this.frames.add(newFrame);
            return;
        }

        this.nextBowling(pins);
    }

    private void nextBowling(int pins) {
        Frame currentFrame = this.currentFrame();

        if (!currentFrame.isFinalFrame() && currentFrame.isFinishBowling()) {
            Frame nextFrame = currentFrame.next(pins);
            this.frames.add(nextFrame);
            return;
        }

        currentFrame.bowling(pins);
    }

    public Frame getFrame(int index) {
        return this.frames.get(index);
    }

    public int lastRound() {
        int lastRound = Math.subtractExact(this.frames.size(), FIRST_ROUND);

        return Math.max(lastRound, MINIMUM_ROUND);
    }

    public Frame currentFrame() {
        return this.getFrame(this.lastRound());
    }
}
