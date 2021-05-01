package bowling.domain.frame;

import bowling.domain.pin.Pin;
import bowling.domain.pin.Pins;
import bowling.exception.NoNextFrameException;

public final class FinalFrame extends Frame {

    public static final int THROW_TWICE = 2;
    public static final int THROW_THREE_TIMES = 3;
    public static final int BONUS_GAME_THRESHOLD = 10;

    private FinalFrame(RoundNumber roundNumber, Pins pins) {
        super(roundNumber, pins);
    }

    public static FinalFrame from(Pins pins) {
        return new FinalFrame(RoundNumber.MAX_ROUND_NUMBER, pins);
    }

    @Override
    public Frame nextFrame() {
        throw new NoNextFrameException();
    }

    @Override
    public void createNextFrame() {
        throw new NoNextFrameException();
    }

    @Override
    public void knockDownPin(Pin pin) {
        pins.knockDownPin(pin);
    }

    @Override
    public boolean isEnded() {
        if (pins.size() == THROW_TWICE) {
            return pins.totalPinCount() < BONUS_GAME_THRESHOLD;
        }
        return pins.size() == THROW_THREE_TIMES;
    }

    @Override
    public boolean isFinalFrame() {
        return true;
    }

    @Override
    public Integer score() {
        return 0;
    }
}
