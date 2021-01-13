package bowling.domain.frame;

import bowling.bowlingexception.IllegalPinRangeException;

import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class DownedPin {

    private static final int MINIMUM_VALUE = 0;
    private static final int MAXIMUM_VALUE = 10;

    private static final List<DownedPin> cachedPins = IntStream.rangeClosed(0, 10)
            .mapToObj(DownedPin::new)
            .collect(toList());

    private final int pin;

    private DownedPin(int pin) {
        this.pin = pin;
    }

    public static DownedPin fromNumber(int downedPin) {
        if (!inValidRange(downedPin)) {
            throw new IllegalPinRangeException();
        }
        return cachedPins.get(downedPin);
    }

    private static boolean inValidRange(int number) {
        return MINIMUM_VALUE <= number && MAXIMUM_VALUE >= number;
    }

    public DownedPin fromPreviousPitch(int downedPin) {
        if (!inValidRange(this.pin + downedPin)) {
            throw new IllegalPinRangeException();
        }
        return cachedPins.get(downedPin);
    }
}
