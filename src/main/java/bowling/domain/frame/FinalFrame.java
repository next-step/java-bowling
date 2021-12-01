package bowling.domain.frame;

import bowling.domain.type.Score;
import bowling.domain.value.FrameNumber;
import bowling.domain.value.FramePins;
import bowling.domain.value.Pins;
import bowling.utils.Preconditions;

public class FinalFrame extends Frame {
    private static final FrameNumber FINAL_FRAME_NUMBER = FrameNumber.from(10);
    private static final int SECOND_PITCH = 2;
    private static final int MAXIMUM_PITCH_COUNT = 3;
    private static final int CHECK_BEFORE_SECOND_PITCH = 1;

    private FinalFrame() {
        this.frameNumber = FINAL_FRAME_NUMBER;
        this.framePins = FramePins.create();
    }

    public static Frame create() {
        return new FinalFrame();
    }

    @Override
    public void pitch(Pins pins) {
        validatePins(pins);

        framePins.addPins(pins);
    }

    private void validatePins(Pins pins) {
        if (!framePins.isFirstPitchStrike() && framePins.isFrameOver(CHECK_BEFORE_SECOND_PITCH)) {
            Preconditions.checkMaximumSize(framePins.calculateTotalPins() + pins.getPins(),
                                           STRIKE_OR_SPARE_COUNT, "최대 투구수를 넘을 수 없습니다.");
        }
    }

    @Override
    void countScore(Pins pins) {
        if (isSecondPitch() && !framePins.isFirstPitchStrike()) {
            pins.addScore(Score.convert(framePins.calculateTotalPins(), false));
            return;
        }

        pins.addScore(Score.convert(pins.getPins(), true));
    }

    @Override
    public boolean isFrameOver() {
        return false;
    }

    @Override
    public boolean isFinalFrameOver() {
        if (isMiss()) {
            return true;
        }

        return isMaximumPitch();
    }

    private boolean isMiss() {
        return isSecondPitch() && framePins.calculateTotalPins() < STRIKE_OR_SPARE_COUNT;
    }

    private boolean isSecondPitch() {
        return framePins.isFrameOver(SECOND_PITCH);
    }

    private boolean isMaximumPitch() {
        return framePins.isFrameOver(MAXIMUM_PITCH_COUNT);
    }
}
