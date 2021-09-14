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

    public static NormalScore ofFirst(Pin pin) {
        return new NormalScore(pin, null);
    }

    public NormalScore createSecondPin(Pin pin) {
        checkFirstStrike(first);

        return new NormalScore(this.first, second);
    }

    private static void checkFirstStrike(Pin pin) {
        if (pin == Pin.of(STRIKE_VALUE)) {
            throw new PinSecondValueException();
        }
    }

}
