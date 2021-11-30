package bowling.domain.frame;

import bowling.domain.type.Score;
import bowling.domain.value.FrameNumber;
import bowling.domain.value.FramePins;
import bowling.domain.value.Pins;
import bowling.utils.Preconditions;

public class NormalFrame extends Frame {
    private static final int MAXIMUM_PITCH_COUNT = 2;

    private NormalFrame(FrameNumber frameNumber) {
        this.frameNumber = frameNumber;
        this.framePins = FramePins.create();
    }

    public static Frame create(FrameNumber frameNumber) {
        return new NormalFrame(frameNumber);
    }

    @Override
    public void pitch(Pins pins) {
        validatePins(pins);

        framePins.addPins(pins);
    }

    private void validatePins(Pins pins) {
        Preconditions.checkMaximumSize(framePins.calculateTotalPins() + pins.getPins(),
                                       STRIKE_OR_SPARE_COUNT, "최대 투구수를 넘을 수 없습니다.");
    }

    @Override
    void countScore(Pins pins) {
        if (isFirstPitch()) {
            pins.addScore(Score.convert(pins.getPins(), true));
            return;
        }

        pins.addScore(Score.convert(framePins.calculateTotalPins(), false));
    }

    private boolean isFirstPitch() {
        return !framePins.isFrameOver(MAXIMUM_PITCH_COUNT);
    }

    @Override
    public boolean isFrameOver() {
        if (isStrike()) {
            return true;
        }

        return isMaximumPitch();
    }

    private boolean isStrike() {
        return STRIKE_OR_SPARE_COUNT == framePins.calculateTotalPins();
    }

    private boolean isMaximumPitch() {
        return framePins.isFrameOver(MAXIMUM_PITCH_COUNT);
    }

    @Override
    public boolean isFinalFrameOver() {
        return false;
    }
}
