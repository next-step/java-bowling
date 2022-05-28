package bowling.domain.frame;

import bowling.domain.State.Pin;
import bowling.domain.State.State;

public abstract class Frame {
    protected final FrameNumber frameNumber;

    public Frame(FrameNumber frameNumber) {
        validate(frameNumber);
        this.frameNumber = frameNumber;
    }

    private void validate(FrameNumber frameNumber) {
        if (frameNumber == null) {
            throw new IllegalArgumentException("frameNumber는 null 일 수 없습니다.");
        }
    }

    public static NormalFrame initialize() {
        return new NormalFrame(FrameNumber.min(), State.ready());
    }

    public abstract Frame bowl(Pin pin);

    public abstract Frame next();

    public abstract boolean isFinal();

    public abstract boolean isDone();

    public FrameNumber number() {
        return frameNumber;
    }
}
