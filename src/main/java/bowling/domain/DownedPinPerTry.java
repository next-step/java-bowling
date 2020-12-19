package bowling.domain;

import bowling.bowlingexception.InvalidDownedPinNumberException;

import java.util.List;
import java.util.stream.IntStream;

import static bowling.domain.BowlingConstant.*;
import static java.util.stream.Collectors.toList;

public class DownedPinPerTry {

    private static final List<DownedPinPerTry> cachedPin = IntStream.rangeClosed(MINIMUM_DOWNED_PIN_NUM, MAXIMUM_DOWNED_PIN_NUM)
            .mapToObj(DownedPinPerTry::new)
            .collect(toList());

    private final int numDownedPin;

    private DownedPinPerTry(int numDownedPin) {
        this.numDownedPin = numDownedPin;
    }

    private static void validateRange(int numDownedPin) {
        if (numDownedPin < MINIMUM_DOWNED_PIN_NUM || numDownedPin > MAXIMUM_DOWNED_PIN_NUM) {
            throw new InvalidDownedPinNumberException();
        }
    }

    public static DownedPinPerTry fromNumber(int number) {
        validateRange(number);
        return cachedPin.get(number);
    }

    public DownedPinPerTry fromFirstTry(DownedPinPerTry downedPinPerTry) {
        validateRange(this.numDownedPin + downedPinPerTry.numDownedPin);
        return fromNumber(downedPinPerTry.numDownedPin);
    }

    public boolean isStrike() {
        return numDownedPin == ALL_PIN_DOWN;
    }
}
