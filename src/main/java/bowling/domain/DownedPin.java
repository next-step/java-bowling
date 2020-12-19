package bowling.domain;

import bowling.bowlingexception.InvalidDownedPinNumberException;

import java.util.List;
import java.util.stream.IntStream;

import static bowling.domain.BowlingConstant.MAXIMUM_DOWNED_PIN_NUM_IN_FRAME;
import static bowling.domain.BowlingConstant.MINIMUM_DOWNED_PIN_NUM_IN_FRAME;
import static java.util.stream.Collectors.toList;

public class DownedPin {

    private static final List<DownedPin> cachedPin = IntStream.rangeClosed(MINIMUM_DOWNED_PIN_NUM_IN_FRAME, MAXIMUM_DOWNED_PIN_NUM_IN_FRAME)
            .mapToObj(DownedPin::new)
            .collect(toList());

    private final int numDownedPin;

    private DownedPin(int numDownedPin) {
        this.numDownedPin = numDownedPin;
    }

    private static void validateRange(int numDownedPin) {
        if (numDownedPin < MINIMUM_DOWNED_PIN_NUM_IN_FRAME || numDownedPin > MAXIMUM_DOWNED_PIN_NUM_IN_FRAME) {
            throw new InvalidDownedPinNumberException();
        }
    }

    public static DownedPin fromNumber(int number) {
        validateRange(number);
        return cachedPin.get(number);
    }

    public DownedPin fromSubordinateTry(DownedPin downedPin) {
        validateRange(this.numDownedPin + downedPin.numDownedPin);
        return fromNumber(downedPin.numDownedPin);
    }

    public boolean isSpare(DownedPin continuousTry) {
        return numDownedPin + continuousTry.numDownedPin == MAXIMUM_DOWNED_PIN_NUM_IN_FRAME;
    }

    public boolean isStrike() {
        return numDownedPin == MAXIMUM_DOWNED_PIN_NUM_IN_FRAME;
    }
}
