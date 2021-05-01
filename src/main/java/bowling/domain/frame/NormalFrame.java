package bowling.domain.frame;

import bowling.domain.pin.Pin;
import bowling.domain.pin.Pins;
import bowling.exception.FramePinCountException;
import bowling.exception.IllegalNormalFrameException;

public final class NormalFrame extends Frame {

    public static final RoundNumber MAX_NORMAL_FRAME_ROUND_NUMBER = new RoundNumber(RoundNumber.MAX - 1);
    private static final int MAX_NORMAL_PIN_COUNT = 10;

    private NormalFrame(RoundNumber roundNumber, Pins pins) {
        super(roundNumber, pins);
    }

    @Override
    public Frame nextFrame() {
        return nextFrame;
    }

    public static Frame of(RoundNumber roundNumber, Pins pins) {
        validateNormalRoundNumber(roundNumber);
        return new NormalFrame(roundNumber, pins);
    }

    private static void validateNormalRoundNumber(RoundNumber roundNumber) {
        if (RoundNumber.MAX_ROUND_NUMBER.equals(roundNumber)) {
            throw new IllegalNormalFrameException();
        }
    }

    public static Frame createFirstFrame() {
        return NormalFrame.of(RoundNumber.firstRoundNumber(), Pins.create());
    }

    @Override
    public void createNextFrame() {
        if (MAX_NORMAL_FRAME_ROUND_NUMBER.equals(roundNumber)) {
            this.nextFrame = FinalFrame.from(Pins.create());
            return;
        }
        this.nextFrame = NormalFrame.of(roundNumber.nextRoundNumber(), Pins.create());
    }

    @Override
    public void knockDownPin(Pin pin) {
        validatePinCount(pin);
        pins.knockDownPin(pin);
    }

    private void validatePinCount(Pin pin) {
        if (pins.totalPinCount() + pin.pinCount() > MAX_NORMAL_PIN_COUNT) {
            throw new FramePinCountException();
        }
    }

    @Override
    public boolean isEnded() {
        return pins.frameStatus() != FrameStatus.NOT_ENDED;
    }

    @Override
    public boolean isFinalFrame() {
        return false;
    }
}
