package bowling.domain.frame;

import bowling.domain.type.BowlingRule;
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
        return framePins.isFrameOver(MAXIMUM_PITCH_COUNT) || isStrike();
    }

    private boolean isStrike() {
        return BowlingRule.STRIKE == framePins.matchForNormalFrame(framePins.getTotalPins(), true);
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
