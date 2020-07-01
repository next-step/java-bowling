package bowling.domain.game;

import bowling.domain.pin.FramePins;
import bowling.domain.pin.FramePinsCreator;
import bowling.domain.pin.Pins;
import bowling.exception.game.CanNotAccessMethod;

import java.util.Objects;

import static bowling.domain.pin.FramePins.MAX_PINS_PER_FRAME;

public class FinalFrame implements Frame {
    public static final int FINAL_FRAME_NUMBER = 10;

    private final FrameNumber frameNumber;
    private FramePins framePins;
    private Pins bonusPins;

    private FinalFrame() {
        this.frameNumber = FrameNumber.of(FINAL_FRAME_NUMBER);
    }

    public static FinalFrame init() {
        return new FinalFrame();
    }

    @Override
    public Frame next(int countOfPins) {
        if (!hasNext()) {
            throw new CanNotAccessMethod();
        }

        if (isBonusFrame()) {
            this.bonusPins = Pins.of(countOfPins);
            return this;
        }

        this.framePins = FramePinsCreator.next(framePins, Pins.of(countOfPins));
        return this;
    }

    private boolean isBonusFrame() {
        return bonusPins == null && framePins != null && framePins.score() == MAX_PINS_PER_FRAME;
    }

    @Override
    public boolean hasNext() {
        return (framePins == null || framePins.score() == MAX_PINS_PER_FRAME) && bonusPins == null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FinalFrame that = (FinalFrame) o;
        return Objects.equals(frameNumber, that.frameNumber) &&
                Objects.equals(framePins, that.framePins) &&
                Objects.equals(bonusPins, that.bonusPins);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frameNumber, framePins, bonusPins);
    }
}
