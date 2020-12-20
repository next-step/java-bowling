package bowling.domain;

import bowling.bowlingexception.InvalidDownedPinNumberException;

import java.util.List;
import java.util.stream.IntStream;

import static bowling.domain.BowlingConstant.MAXIMUM_NUMBER_OF_DOWNED_PIN;
import static bowling.domain.BowlingConstant.MINIMUM_NUMBER_OF_DOWNED_PIN;
import static java.util.stream.Collectors.toList;

public class DownedPin {

    private static final List<DownedPin> cachedPin = IntStream.rangeClosed(MINIMUM_NUMBER_OF_DOWNED_PIN, MAXIMUM_NUMBER_OF_DOWNED_PIN)
            .mapToObj(DownedPin::new)
            .collect(toList());

    private final int numDownedPin;

    private DownedPin(int numDownedPin) {
        this.numDownedPin = numDownedPin;
    }

    private static void validateRange(int numDownedPin) {
        if (numDownedPin < MINIMUM_NUMBER_OF_DOWNED_PIN || numDownedPin > MAXIMUM_NUMBER_OF_DOWNED_PIN) {
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
        return !isStrike() && numDownedPin + continuousTry.numDownedPin == MAXIMUM_NUMBER_OF_DOWNED_PIN;
    }

    public boolean isGutter() {
        return numDownedPin == MINIMUM_NUMBER_OF_DOWNED_PIN;
    }

    public boolean isStrike() {
        return numDownedPin == MAXIMUM_NUMBER_OF_DOWNED_PIN;
    }

    public int getNumDownedPin() {
        return numDownedPin;
    }
}
