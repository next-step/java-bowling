package bowling.domain.frame;

import bowling.bowlingexception.InvalidDownedPinNumberException;

import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class DownedPin {

    public static final int MAXIMUM_NUMBER_OF_DOWNED_PIN = 10;
    public static final int MINIMUM_NUMBER_OF_DOWNED_PIN = 0;

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

    public static DownedPin fromNumber(int numDownedPin) {
        validateRange(numDownedPin);
        return cachedPin.get(numDownedPin);
    }

    public DownedPin fromPreviousPitch(int numDownedPin) {
        validateRange(this.numDownedPin + numDownedPin);
        return cachedPin.get(numDownedPin);
    }

    public boolean isTen() {
        return numDownedPin == MAXIMUM_NUMBER_OF_DOWNED_PIN;
    }
}
