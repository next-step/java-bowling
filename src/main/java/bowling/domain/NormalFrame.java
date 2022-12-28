package bowling.domain;

import bowling.domain.state.Ready;
import bowling.domain.state.Status;

public class NormalFrame implements Frame {

    public static final int MIN_FRAMENUMBER = 1;
    public static final int MAX_FRAMENUMBER = 9;

    private final int frameNumber;
    private Status status;

    public NormalFrame(int frameNumber) {
        validate(frameNumber);
        this.frameNumber = frameNumber;
        status = new Ready();
    }

    private void validate(int frameNumber) {
        if (frameNumber < MIN_FRAMENUMBER) {
            throw new IllegalArgumentException("Frame은 1번부터 시작합니다.");
        }
        if (frameNumber > MAX_FRAMENUMBER) {
            throw new IllegalArgumentException("NormalFrame은 9번까지만 존재합니다.");
        }
    }

    public int frameNumber() {
        return frameNumber;
    }

    @Override
    public void bowl(Pin pin) {
        status = status.bowl(pin);
    }

    @Override
    public boolean isFinished() {
        return status.isFinished();
    }

    @Override
    public Frame nextFrame() {
        if (isFinished()) {
            return new NormalFrame(frameNumber + 1);
        }
        return this;
    }

    @Override
    public String toString() {
        return status.toString();
    }
}
