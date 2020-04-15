package bowling.domain.frame;

import bowling.domain.frame.state.Ready;
import bowling.domain.frame.state.State;
import bowling.exception.BowlingException;

public class NormalFrame implements Frame {

    private static final String MAX_FRAME_MESSAGE = "1~10번 프레임 까지만 등록 가능 합니다.";
    private static final String CAN_THROW_TWICE = "1~9번 프레임은 2회 던질수 있습니다.";
    private static final int NEXT_TURN_SIZE = 2;
    private static final int MIN_FRAME_NUMBER = 1;

    private final int frameNumber;
    private final Frame nextFrame;
    private final State state;

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

    public Frame createNext() {
        if (frameNumber == MAX_FRAME_NUMBER - 1) {
            return new NormalFrame(frameNumber, new FinalFrame(), state);
        }

        NormalFrame next = new NormalFrame(frameNumber + 1);
        return new NormalFrame(frameNumber, next, state);
    }

    @Override
    public boolean isFinish() {
        if (state.isFinish()) {
            return true;
        }

        return false;
    }

    @Override
    public Frame bowl(final int pinCount) {
        State bowl = state.bowl(pinCount);
        return new NormalFrame(frameNumber, nextFrame, bowl);
    }

    @Override
    public Frame getNext() {
        return nextFrame;
    }
}
