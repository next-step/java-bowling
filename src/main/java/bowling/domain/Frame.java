package bowling.domain;

import java.util.Objects;

public class Frame {
    private FrameResult frameResult;
    private Frame nextFrame;

    Frame(FrameResult frameResult, Frame nextFrame) {
        this.frameResult = frameResult;
        this.nextFrame = nextFrame;
    }

    public static Frame bowlFirst(int numberOfHitPin) {
        return new Frame(FrameResultFactory.create(numberOfHitPin), null);
    }

    public Frame bowlSecond(int secondNumberOfHitPin) {
        NormalFrameResult previousFrameResult = (NormalFrameResult) this.frameResult;
        NormalFrameResult afterFrameResult = previousFrameResult.secondThrow(secondNumberOfHitPin);

        return new Frame(afterFrameResult, null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Frame frame = (Frame) o;
        return Objects.equals(frameResult, frame.frameResult) &&
                Objects.equals(nextFrame, frame.nextFrame);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frameResult, nextFrame);
    }

    @Override
    public String toString() {
        return "Frame{" +
                "frameResult=" + frameResult +
                ", nextFrame=" + nextFrame +
                '}';
    }
}
