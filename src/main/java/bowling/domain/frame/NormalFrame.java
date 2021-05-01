package bowling.domain.frame;

import java.util.Optional;

import bowling.domain.state.BowlingPin;

public class NormalFrame extends Frame {
    private static final int FIRST_FRAME_NUMBER = 1;

    private final Frame nextFrame;

    private NormalFrame(int frameNumber) {
        super(frameNumber);
        this.nextFrame = next();
    }

    public static Frame of(int frameNumber) {
        return new NormalFrame(frameNumber);
    }

    public static Frame init() {
        return new NormalFrame(FIRST_FRAME_NUMBER);
    }

    private Frame next() {
        int nextFrameIndex = frameNumber + 1;
        if (nextFrameIndex == TOTAL_FRAME_SIZE) {
            return FinalFrame.of(nextFrameIndex);
        }
        return NormalFrame.of(nextFrameIndex);
    }

    @Override
    public Frame bowl(BowlingPin bowlingPin) {
        states.add(currentState().bowl(bowlingPin));
        if (!isDone()) {
            return this;
        }
        return nextFrame;
    }

    @Override
    public boolean isDone() {
        if (states.isEmpty()) {
            return false;
        }
        return states.lastState().isDone();
    }

    @Override
    public Optional<Frame> getNext() {
        return Optional.of(nextFrame);
    }
}
