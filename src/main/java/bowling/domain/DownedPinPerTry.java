package bowling.domain;

import bowling.bowlingexception.InvalidDownedPinNumberException;

import java.util.List;
import java.util.stream.IntStream;

import static bowling.domain.BowlingConstant.MAXIMUM_DOWNED_PIN_NUM_IN_FRAME;
import static bowling.domain.BowlingConstant.MINIMUM_DOWNED_PIN_NUM_IN_FRAME;
import static java.util.stream.Collectors.toList;

public class DownedPinPerTry {

    private static final List<DownedPinPerTry> cachedPin = IntStream.rangeClosed(MINIMUM_DOWNED_PIN_NUM_IN_FRAME, MAXIMUM_DOWNED_PIN_NUM_IN_FRAME)
            .mapToObj(DownedPinPerTry::new)
            .collect(toList());

    private final int numDownedPin;

    private DownedPinPerTry(int numDownedPin) {
        this.numDownedPin = numDownedPin;
    }

    private static void validateRange(int numDownedPin) {
        if (numDownedPin < MINIMUM_DOWNED_PIN_NUM_IN_FRAME || numDownedPin > MAXIMUM_DOWNED_PIN_NUM_IN_FRAME) {
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

    public boolean isSpare(DownedPinPerTry continuousTry) {
        return numDownedPin + continuousTry.numDownedPin == MAXIMUM_DOWNED_PIN_NUM_IN_FRAME;
    }

    public boolean isStrike() {
        return numDownedPin == MAXIMUM_DOWNED_PIN_NUM_IN_FRAME;
    }
}
