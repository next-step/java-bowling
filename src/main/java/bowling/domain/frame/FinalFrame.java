package bowling.domain.frame;

import bowling.domain.type.Score;
import bowling.domain.value.FrameNumber;
import bowling.domain.value.FramePins;
import bowling.domain.value.Pins;

public class FinalFrame extends Frame {
    private static final FrameNumber FINAL_FRAME_NUMBER = FrameNumber.from(10);
    private static final int MAXIMUM_PITCH_COUNT = 3;
    private static final int FIRST_PITCH = 1;
    private static final int SECOND_PITCH = 2;

    private final FramePins framePins;

    private FinalFrame() {
        this.framePins = FramePins.create();
    }

    public static Frame create() {
        return new FinalFrame();
    }

    @Override
    public void pitch(Pins pins) {
        framePins.addPins(pins);
        addScore(pins);
    }

    private void addScore(Pins pins) {
        if (isSecondPitch()) {
            pins.addScore(framePins.isFirstPitchStrike() ?
                                  Score.convert(pins.getPins(), true) :
                                  Score.convert(framePins.getTotalPins(), false));
            return;
        }

        pins.addScore(Score.convert(pins.getPins(), true));
    }

    @Override
    FramePins getPins() {
        return framePins;
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

    private boolean isFirstPitch() {
        return framePins.isFrameOver(FIRST_PITCH);
    }

    private boolean isSecondPitch() {
        return framePins.isFrameOver(SECOND_PITCH);
    }

    @Override
    boolean isSameFrameNumber(FrameNumber frameNumber) {
        return FINAL_FRAME_NUMBER.equals(frameNumber);
    }

    @Override
    FrameNumber getFrameNumber() {
        return FINAL_FRAME_NUMBER;
    }
}
