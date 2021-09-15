package bowling.domain;

import java.util.Objects;

public class NormalFrame extends Frame{
    private Frame nextFrame;

    public NormalFrame(int hitNumberOfPin, int frameIndex) {
        super(hitNumberOfPin, frameIndex);
    }

    public Frame nextFrame(int hitNumberOfPin) {
        if (frameIndex.isNotStartFrame() && !Status.STRIKE.equals(status)) {
            score.validateExistSecondScore();
        }

        if (frameIndex.isBeforeFinalFrame()) {
            nextFrame = new FinalFrame(hitNumberOfPin);
            return nextFrame;
        }
        nextFrame = new NormalFrame(hitNumberOfPin, frameIndex.getValue() + 1);
        return nextFrame;
    }

    public static NormalFrame of() {
        return new NormalFrame(0, EMPTY_FRAME);
    }

    public Frame getNextFrame() {
        return nextFrame;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalFrame frame = (NormalFrame) o;
        return Objects.equals(score, frame.score) && status == frame.status && Objects.equals(nextFrame, frame.nextFrame);
    }

    @Override
    public int hashCode() {
        return Objects.hash(score, status, nextFrame);
    }
}
