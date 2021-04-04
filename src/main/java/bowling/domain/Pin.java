package bowling.domain;

public class Pin {
    private static final String MAX_OVER_PINS = "넘어뜨리는 볼링핀은 10개가 최대입니다.";
    private static final String MIN_UNDER_PINS = "넘어뜨리는 볼링핀은 0개 미만이 안됩니다.";
    private static final int MAX_PINS = 10;
    private static final int MIN_PINS = 0;
    private static final boolean IS_FIRST = true;

    private final int pin;

    private Pin(int pin) {
        validMaxPins(pin);
        validMinPins(pin);
        this.pin = pin;
    }

    public static Pin of(int pin) {
        return new Pin(pin);
    }

    public boolean isStrike() {
        return ScoreRule.STRIKE == ScoreRule.of(pin, IS_FIRST);
    }

    public ScoreRule scoreRule(boolean isFirst) {
        return ScoreRule.of(pin, isFirst);
    }

    public int accumulated(int downPins) {
        return pin + downPins;
    }

    public int pin() {
        return pin;
    }

    private void validMinPins(int pin) {
        if (pin < MIN_PINS) {
            throw new IllegalArgumentException(MIN_UNDER_PINS);
        }
    }

    private void validMaxPins(int pin) {
        if (pin > MAX_PINS) {
            throw new IllegalArgumentException(MAX_OVER_PINS);
        }
    }
}


