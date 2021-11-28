package bowling.domain.frame;

import bowling.domain.value.FrameNumber;
import bowling.domain.value.FramePins;
import bowling.domain.value.Pins;

public class NormalFrame extends Frame {
    private static final int MAXIMUM_PITCH_COUNT = 2;

    private final FrameNumber frameNumber;
    private final FramePins framePins;

    private NormalFrame(FrameNumber frameNumber) {
        this.frameNumber = frameNumber;
        this.framePins = FramePins.create();
    }

    public static Frame create(FrameNumber frameNumber) {
        return new NormalFrame(frameNumber);
    }

    @Override
    public void pitch(Pins pins) {
        framePins.addPins(pins);
    }

    @Override
    public boolean isFrameOver() {
        if (isStrike()) {
            return true;
        }

        return framePins.isFrameOver(MAXIMUM_PITCH_COUNT);
    }

    private boolean isStrike() {
        return STRIKE_OR_SPARE_COUNT == framePins.getTotalPins();
    }

    @Override
    public boolean isLastFrameOver() {
        return false;
    }

    @Override
    FramePins getPins() {
        return framePins;
    }

    @Override
    boolean isSameFrameNumber(FrameNumber frameNumber) {
        return this.frameNumber.equals(frameNumber);
    }

    @Override
    FrameNumber getFrameNumber() {
        return frameNumber;
    }
}
