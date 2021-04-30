package bowling.domain.frame;

import bowling.domain.pin.Pin;
import bowling.domain.pin.Pins;
import bowling.exception.NoNextFrameException;

public final class FinalFrame extends Frame {

    private FinalFrame(RoundNumber roundNumber, Pins pins) {
        super(roundNumber, pins);
    }

    public static FinalFrame from(Pins pins) {
        return new FinalFrame(RoundNumber.MAX_ROUND_NUMBER, pins);
    }

    @Override
    public Frame createNextFrame() {
        throw new NoNextFrameException();
    }

    @Override
    public void knockDownPin(Pin pin) {
        pins.knockDownPin(pin);
    }

    @Override
    public boolean isEnded() {
        return pins.frameStatus() != FrameStatus.NOT_ENDED;
    }
}
