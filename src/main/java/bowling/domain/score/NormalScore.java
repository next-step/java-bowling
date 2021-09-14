package bowling.domain.score;

import bowling.exception.Pin.PinSecondValueException;

public class NormalScore extends Score {

    private static final int STRIKE_VALUE = 10;

    private NormalScore(Pin first, Pin second) {
        super(first, second);
    }

    public static NormalScore empty() {
        return new NormalScore(null, null);
    }

    @Override
    public NormalScore createFirstPin(Pin pin) {
        return new NormalScore(pin, null);
    }

    @Override
    public NormalScore createSecondPin(Pin pin) {
        checkFirstStrike(this.first);

        return new NormalScore(this.first, pin);
    }

    private static void checkFirstStrike(Pin pin) {
        if (pin == Pin.of(STRIKE_VALUE)) {
            throw new PinSecondValueException();
        }
    }

    public boolean isNext() {
        return first != Pin.of(STRIKE_VALUE);
    }

}
