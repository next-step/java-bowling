package frame.info;

import frame.Frame;
import score.framescore.FrameScore;

import java.util.Objects;

public class FrameInfo {
    private final FrameNumber frameNumber;
    private final Frame nextFrame;

    private FrameInfo(FrameNumber frameNumber, Frame nextFrame) {
        this.frameNumber = frameNumber;
        this.nextFrame = nextFrame;
    }

    private FrameInfo(int number, Frame nextFrame) {
        this.frameNumber = new FrameNumber(number);
        this.nextFrame = nextFrame;
    }

    public static FrameInfo make(int number) {
        return new FrameInfo(number, null);
    }

    public FrameInfo makeWithNext(Frame nextFrame) {
        return new FrameInfo(frameNumber, nextFrame);
    }

    public Integer nextNumber() {
        return frameNumber.next();
    }

    public FrameScore calculateNext(FrameScore frameScore) {
        if (nextFrame == null) {
            return frameScore;
        }
        return nextFrame.addNextScore(frameScore);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FrameInfo frameInfo = (FrameInfo) o;
        return Objects.equals(frameNumber, frameInfo.frameNumber) &&
                Objects.equals(nextFrame, frameInfo.nextFrame);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frameNumber, nextFrame);
    }

    public int getNumber() {
        return frameNumber.getNumber();
    }
}
