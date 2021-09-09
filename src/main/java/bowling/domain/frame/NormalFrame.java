package bowling.domain.frame;

import bowling.domain.pin.Pins;
import bowling.exception.FrameNotCorrectException;

public final class NormalFrame extends Frame {

    public static final int DEFAULT_ROUND_NUMBER = 1;
    private static final int MAX_SIZE = 2;
    private static final int MAX_KNOCK_DOWN_NUMBER = 10;
    private static final int ROUND_BEFORE_LAST_ROUND = 9;

    private NormalFrame(final int roundNumber) {
        super(roundNumber, Pins.of());
    }

    private NormalFrame(final int roundNumber, final Pins pins) {
        super(roundNumber, pins);
    }

    public static Frame of(final int roundNumber, final int... number) {
        return new NormalFrame(roundNumber, Pins.of(number));
    }

    public static Frame of(final int roundNumber) {
        return new NormalFrame(roundNumber);
    }

    @Override
    protected void validateFrame(final Pins pins) {
        if ( pins.sumPins() > MAX_KNOCK_DOWN_NUMBER) {
            throw new FrameNotCorrectException();
        }
    }

    @Override
    public void inputKnockDownNumber(final int knockDownNumber) {
        pins.add(knockDownNumber);
        validateFrame(pins);
    }

    @Override
    public boolean isFinished() {
        if (pins.firstPin().isMaximum()) {
            return true;
        }
        return pins.size() == MAX_SIZE;
    }

    @Override
    public Frame nextFrame() {
        if (pins.size() == 0) {
            return this;
        }

        if (pins.size() == 1 && !pins.firstPin().isMaximum()) {
            return this;
        }

        if (roundNumber == ROUND_BEFORE_LAST_ROUND) {
            return FinalFrame.of();
        }
        return new NormalFrame(roundNumber + 1);
    }
}