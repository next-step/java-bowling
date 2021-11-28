package bowling.domain.frame;

import bowling.domain.type.Score;
import bowling.domain.value.FrameNumber;
import bowling.domain.value.FramePins;
import bowling.domain.value.Pins;

public class FinalFrame extends Frame {
    private static final FrameNumber FINAL_FRAME_NUMBER = FrameNumber.from(10);
    private static final int MAXIMUM_PITCH_COUNT = 3;
    private static final int SECOND_PITCH = 2;

    private FinalFrame() {
        this.frameNumber = FINAL_FRAME_NUMBER;
        this.framePins = FramePins.create();
    }

    public static Frame create() {
        return new FinalFrame();
    }

    @Override
    public void pitch(Pins pins) {
        framePins.addPins(pins);
    }

    @Override
    void countScore(Pins pins) {
        if (isSecondPitch()) {
            pins.addScore(framePins.isFirstPitchStrike() ?
                                  Score.convert(pins.getPins(), true) :
                                  Score.convert(framePins.getTotalPins(), false));
            return;
        }

        pins.addScore(Score.convert(pins.getPins(), true));
    }

    @Override
    public boolean isFrameOver() {
        return false;
    }

    @Override
    public boolean isLastFrameOver() {
        if (isMiss()) {
            return true;
        }

        return framePins.isFrameOver(MAXIMUM_PITCH_COUNT);
    }

    private boolean isMiss() {
        return isSecondPitch() && framePins.getTotalPins() < STRIKE_OR_SPARE_COUNT;
    }

    private boolean isSecondPitch() {
        return framePins.isFrameOver(SECOND_PITCH);
    }
}
