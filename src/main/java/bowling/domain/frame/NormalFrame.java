package bowling.domain.frame;

import bowling.domain.pin.Pin;
import bowling.domain.pin.Pins;
import bowling.exception.IllegalNormalFrameException;

public final class NormalFrame extends Frame {

    public static final RoundNumber MAX_NORMAL_FRAME_ROUND_NUMBER = new RoundNumber(RoundNumber.MAX - 1);

    private NormalFrame(RoundNumber roundNumber, Pins pins) {
        super(roundNumber, pins);
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
    public Frame createNextFrame() {
        if (MAX_NORMAL_FRAME_ROUND_NUMBER.equals(roundNumber)) {
            return FinalFrame.from(Pins.create());
        }
        return NormalFrame.of(roundNumber.nextRoundNumber(), Pins.create());
    }

    @Override
    public void knockDownPin(Pin pin) {
        // TODO: validate pin count
        pins.knockDownPin(pin);
    }

    @Override
    public boolean isEnded() {
        return pins.frameStatus() != FrameStatus.NOT_ENDED;
    }
}
