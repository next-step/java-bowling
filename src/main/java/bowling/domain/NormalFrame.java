package bowling.domain;

import bowling.domain.state.Ready;
import bowling.domain.state.Status;

import java.util.Objects;

public class NormalFrame implements Frame {

    public static final int MIN_FRAMENUMBER = 1;
    public static final int MAX_FRAMENUMBER = 9;

    private final int frameNumber;
    private Status status;

    NormalFrame(int frameNumber, Status status) {
        validate(frameNumber);
        this.frameNumber = frameNumber;
        this.status = status;
    }

    public static Frame init(int frameNumber) {
        return new NormalFrame(frameNumber, new Ready());
    }

    private static void validate(int frameNumber) {
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
        if (!isFinished()) {
            return this;
        }
        if (frameNumber < MAX_FRAMENUMBER) {
            return NormalFrame.init(frameNumber + 1);
        }
        return FinalFrame.init();
    }

    @Override
    public String toString() {
        return status.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalFrame that = (NormalFrame) o;
        return frameNumber == that.frameNumber && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frameNumber, status);
    }
}
