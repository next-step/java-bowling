package bowling;


import java.util.List;

import static bowling.CommonConstans.*;

public class Pin {

    private final int pin;

    public Pin(int pin) {
        validMinPin(pin);
        validMaxPin(pin);
        this.pin = pin;
    }

    public static Pin of(int pin) {
        return new Pin(pin);
    }

    private static void validMinPin(int pin) {
        if (pin < MIN_PINS) {
            throw new IllegalArgumentException(MAX_OVER_PINS);
        }
    }

    private static void validMaxPin(int pin) {
        if (pin > MAX_PINS) {
            throw new IllegalArgumentException(MIN_UNDER_PINS);
        }
    }

    public static void validMaxPins(List<Pin> pins) {
        if (accumulatedPins(pins) > MAX_PINS) {
            throw new IllegalArgumentException(MAX_OVER_PINS);
        }
    }

    public static int accumulatedPins(List<Pin> pins) {
        return pins.stream()
                .skip(skipSize(pins))
                .limit(NORMAL_PINS_MAX_SIZE)
                .mapToInt(Pin::pin)
                .sum();
    }

    private static long skipSize(List<Pin> pins) {
        int result;
        for (result = ZERO; result < pins.size() - MINUS_SIZE_ONE; result++) {
            if (!pins.get(result).isStrike()) {
                break;
            }
        }
        return result;
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


}
