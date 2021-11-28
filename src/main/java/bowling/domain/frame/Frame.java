package bowling.domain.frame;

import bowling.domain.value.FrameNumber;
import bowling.domain.value.FramePins;
import bowling.domain.value.Pins;

public abstract class Frame {
    protected static final int STRIKE_OR_SPARE_COUNT = 10;

    private Frame nextFrame;

    public void setNext(Frame nextFrame) {
        this.nextFrame = nextFrame;
    }

    abstract boolean isLastFrameOver();

    abstract boolean isFrameOver();

    abstract void pitch(Pins pins);

    abstract void countScore(Pins pins);

    abstract FramePins getPins();

    abstract boolean isSameFrameNumber(FrameNumber frameNumber);

    abstract FrameNumber getFrameNumber();

    public final void knockedDown(Pins pins) {
        if (isFrameOver()) {
            nextFrame.knockedDown(pins);
            return;
        }

        pitch(pins);
        countScore(pins);
    }

    public final FrameNumber getCurrentFrameNumber() {
        if (isFrameOver()) {
            return nextFrame.getCurrentFrameNumber();
        }

        return getFrameNumber();
    }

    public final boolean isGameOver() {
        if (isFrameOver()) {
            return nextFrame.isGameOver();
        }

        return isLastFrameOver();
    }

    public final FramePins getPins(FrameNumber frameNumber) {
        if (isSameFrameNumber(frameNumber)) {
            return getPins();
        }

        return nextFrame.getPins(frameNumber);
    }
}
