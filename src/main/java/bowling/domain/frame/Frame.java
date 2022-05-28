package bowling.domain.frame;

import bowling.domain.State.State;

import static bowling.view.OutputView.STATE_FORMAT;

public abstract class Frame {
    protected final FrameNumber frameNumber;
    protected final State state;

    public Frame(FrameNumber frameNumber) {
        this(frameNumber, State.ready());
    }

    public Frame(FrameNumber frameNumber, State state) {
        validate(frameNumber, state);
        this.frameNumber = frameNumber;
        this.state = state;
    }

    private void validate(FrameNumber frameNumber, State state) {
        if (frameNumber == null) {
            throw new IllegalArgumentException("frameNumber는 null 일 수 없습니다.");
        }

        if (state == null) {
            throw new IllegalArgumentException("state는 null 일 수 없습니다.");
        }
    }

    public static NormalFrame initialize() {
        return new NormalFrame(FrameNumber.min(), State.ready());
    }

    public abstract Frame next();

    public abstract boolean isFinal();

    public abstract boolean isDone();

    public FrameNumber number() {
        return frameNumber;
    }

    @Override
    public String toString() {
        return String.format(STATE_FORMAT, state.toString());
    }
}
