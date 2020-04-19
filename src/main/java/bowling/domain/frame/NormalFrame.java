package bowling.domain.frame;

import bowling.domain.frame.state.Ready;
import bowling.domain.frame.state.State;
import bowling.domain.score.Calculator;
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
    public Calculator getCurrenteCalculator() {
        return state.getCurrentCalculator();
    }

    @Override
    public Calculator getScoreCalculate(Calculator calculator) {
        return state.getScoreCalculate(calculator);
    }
}
