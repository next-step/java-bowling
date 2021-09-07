package bowling;


import static bowling.CommonConstans.*;

public class Pin {

    private final int pin;

    public Pin(int pin) {
        validMaxPins(pin);
        validMinPins(pin);
        this.pin = pin;
    }

    public boolean isStrike() {
        return ScoreRule.STRIKE == ScoreRule.of(pin, IS_FIRST);
    }

    public ScoreRule scoreRule(boolean isFirst) {
        return ScoreRule.of(pin, isFirst);
    }

    public int pin() {
        return pin;
    }

    public static Pin of(int pin) {
        return new Pin(pin);
    }

    private static void validMinPins(int pin) {
        if (pin < MIN_PINS) {
            throw new IllegalArgumentException(MAX_OVER_PINS);
        }
    }

    private static void validMaxPins(int pin) {
        if (pin > MAX_PINS) {
            throw new IllegalArgumentException(MIN_UNDER_PINS);
        }
    }
}
