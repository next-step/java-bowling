package bowling.domain;

import bowling.bowlingexception.InvalidDownedPinNumberException;

import java.util.List;
import java.util.stream.IntStream;

import static bowling.domain.BowlingConstant.MAXIMUM_DOWNED_PIN_NUM;
import static bowling.domain.BowlingConstant.MINIMUM_DOWNED_PIN_NUM;
import static java.util.stream.Collectors.toList;

public class DownedPin {

    private static final List<DownedPin> cachedPin = IntStream.rangeClosed(MINIMUM_DOWNED_PIN_NUM, MAXIMUM_DOWNED_PIN_NUM)
            .mapToObj(DownedPin::new)
            .collect(toList());

    private final int numDownedPin;

    private DownedPin(int numDownedPin) {
        this.numDownedPin = numDownedPin;
    }

    private static void validateRange(int numDownedPin) {
        if (numDownedPin < MINIMUM_DOWNED_PIN_NUM || numDownedPin > MAXIMUM_DOWNED_PIN_NUM) {
            throw new InvalidDownedPinNumberException();
        }
    }

    public static DownedPin from(int number) {
        validateRange(number);
        return cachedPin.get(number);
    }
}
