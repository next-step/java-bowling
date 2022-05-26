package bowling.domain.frame;

import java.util.ArrayList;
import java.util.List;

public class Frames {

    private static final int STRIKE_OR_SPARE = 10;
    private static final int START_INDEX = 0;
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
                || !this.frames.get(this.lastRound()).isFinalFrame();
    }

    public boolean isBonus() {
        Frame currentFrame = this.frames.get(this.lastRound());

        return currentFrame.isFinalFrame()
                && currentFrame.totalCount() == STRIKE_OR_SPARE;
    }

    public void bowling(int count) {
        if (this.frames.isEmpty()) {
            Frame newFrame = NormalFrame.bowling(START_INDEX, count);
            this.frames.add(newFrame);
            return;
        }

        this.nextBowling(count);
    }

    private void nextBowling(int count) {
        int lastRound = this.lastRound();
        Frame currentFrame = this.frames.get(lastRound);

        if (currentFrame.isFinishBowling()) {
            Frame nextFrame = currentFrame.next(count);
            this.frames.add(nextFrame);
            return;
        }

        currentFrame.bowling(count);
    }

    private int lastRound() {
        return Math.subtractExact(this.frames.size(), ONE);
    }

    public void print() {
        this.frames.forEach(System.out::println);
    }
}
