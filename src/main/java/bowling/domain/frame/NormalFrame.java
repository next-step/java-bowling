package bowling.domain.frame;

import bowling.domain.score.Score;
import bowling.domain.frame.state.*;
import bowling.exception.BowlingException;

public class NormalFrame implements Frame {

    private static final String MAX_FRAME_MESSAGE = "1~10번 프레임 까지만 등록 가능 합니다.";
    private static final int MIN_FRAME_NUMBER = 1;

    private final int frameNumber;
    private Frame nextFrame;
    private State state;

    public NormalFrame() {
        this(MIN_FRAME_NUMBER);
    }

    public NormalFrame(final int frameNumber) {
        this(frameNumber, null, new Ready());
    }

    public NormalFrame(final int frameNumber, final Frame nextFrame, final State state) {
        validateFrameNumber(frameNumber);
        this.frameNumber = frameNumber;
        this.nextFrame = nextFrame;
        this.state = state;
    }

    private void validateFrameNumber(final int frameNumber) {
        if (frameNumber < MIN_FRAME_NUMBER || frameNumber > MAX_FRAME_NUMBER) {
            throw new BowlingException(MAX_FRAME_MESSAGE);
        }
    }

    private Score makeStrikeScore() {
        Score score = state.getScore();

        Frame next = nextFrame;
        if (next == null) {
            return null;
        }

        if (next.getState() instanceof Miss
                || next.getState() instanceof Spare
                || next.getState() instanceof Gutter) {
            score = score.addScore(next.getState().getScore());
        }

        return score;
    }

    private Score makeSpareScore() {
        Score score = state.getScore();

        Frame next = nextFrame;
        if (next == null) {
            return null;
        }

        if (next.getState() instanceof Miss
                || next.getState() instanceof Strike
                || next.getState() instanceof Gutter
                || next.getState() instanceof FirstBowl) {
            score = score.addScore(next.getState().getFirstScore());
        }

        return score;
    }

    @Override
    public Frame bowl(final int pinCount) {
        if (isFinish()) {
            throw new BowlingException(State.CANT_THROW_BALL);
        }

        state = state.bowl(pinCount);
        return this;
    }

    @Override
    public boolean isFinish() {
        return state.isFinish();
    }

    @Override
    public Frame createNext() {
        if (frameNumber == MAX_FRAME_NUMBER - 1) {
            nextFrame = new FinalFrame();
            return nextFrame;
        }

        nextFrame = new NormalFrame(frameNumber + 1);
        return nextFrame;
    }

    @Override
    public Frame getNext() {
        return nextFrame;
    }

    @Override
    public State getState() {
        return state;
    }

    @Override
    public Frame findLast() {
        if (nextFrame == null) {
            return this;
        }
        return nextFrame.findLast();
    }

    @Override
    public Score getScore() {
        if (state instanceof Strike) {
            return makeStrikeScore();
        }

        if (state instanceof Spare) {
            return makeSpareScore();
        }
        return null;
    }
}
