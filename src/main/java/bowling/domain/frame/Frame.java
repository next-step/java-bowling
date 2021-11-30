package bowling.domain.frame;

import bowling.annotations.GetterForUI;
import bowling.domain.value.FrameNumber;
import bowling.domain.value.FramePins;
import bowling.domain.value.Pins;

public abstract class Frame {
    protected static final int STRIKE_OR_SPARE_COUNT = 10;

    protected FrameNumber frameNumber;
    protected FramePins framePins;

    public abstract boolean isFrameOver();

    public abstract boolean isFinalFrameOver();

    abstract void pitch(Pins pins);

    abstract void countScore(Pins pins);

    public final void knockedDown(Pins pins) {
        pitch(pins);
        countScore(pins);
    }

    @GetterForUI
    public final FrameNumber getCurrentFrameNumber() {
        return frameNumber;
    }

    @GetterForUI
    public FramePins getPins() {
        return framePins;
    }
}
