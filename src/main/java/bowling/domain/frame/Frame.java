package bowling.domain.frame;

import bowling.domain.value.FrameNumber;
import bowling.domain.value.FramePins;
import bowling.domain.value.Pins;

public abstract class Frame {
    protected static final int STRIKE_OR_SPARE_COUNT = 10;

    private Frame nextFrame;
    protected FrameNumber frameNumber;
    protected FramePins framePins;

    public void setNext(Frame nextFrame) {
        this.nextFrame = nextFrame;
    }

    abstract boolean isFrameOver();

    abstract boolean isLastFrameOver();

    abstract void pitch(Pins pins);

    abstract void countScore(Pins pins);

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

        return frameNumber;
    }

    public final boolean isGameOver() {
        if (isFrameOver()) {
            return nextFrame.isGameOver();
        }

        return isLastFrameOver();
    }

    public final FramePins getPins(FrameNumber frameNumber) {
        if (this.frameNumber.equals(frameNumber)) {
            return framePins;
        }

        return nextFrame.getPins(frameNumber);
    }
}
