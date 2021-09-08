package bowling.domain.frame;

import bowling.domain.pin.Pins;

public final class FinalFrame extends Frame {

    private static final int FINAL_FRAME_ROUND_NUMBER = 10;
    private static final int MAX_SIZE = 3;
    private static final int MIN_SIZE = 2;

    private FinalFrame(final int roundNumber, final Pins pins) {
        super(roundNumber, pins);
    }

    public static Frame of() {
        return new FinalFrame(FINAL_FRAME_ROUND_NUMBER, Pins.of());
    }

    public static Frame of(final int... pins) {
        return new FinalFrame(FINAL_FRAME_ROUND_NUMBER, Pins.of(pins));
    }

    @Override
    public void inputKnockDownNumber(final int knockDownNumber) {
        pins.add(knockDownNumber);
    }

    @Override
    public boolean isFinished() {
        if (pins.size() == MAX_SIZE) {
            return true;
        }
        if (pins.size() < MIN_SIZE) {
            return false;
        }
        return pins.sumPins() < 10;
    }

    @Override
    public Frame nextFrame() {
        return this;
    }
}
