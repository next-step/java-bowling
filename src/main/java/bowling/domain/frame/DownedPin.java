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
        if (!isValid(downedPin)) {
            throw new IllegalPinRangeException();
        }
        return cachedPins.get(downedPin);
    }

    private static boolean isValid(int number) {
        return MINIMUM_VALUE <= number && number <= MAXIMUM_VALUE;
    }

    public DownedPin fromPreviousPitch(int downedPin) {
        if (!isValid(this.pin + downedPin)) {
            throw new IllegalPinRangeException();
        }
        return cachedPins.get(downedPin);
    }

    public boolean isStrike() {
        return pin == 10;
    }

    public boolean isSpare(DownedPin additionalPitch) {
        return pin + additionalPitch.pin == 10;
    }

    public static int getDownedPinsOnSpare() {
        return getWholeClearValue();
    }

    public static int getDownedPinsOnStrike() {
        return getWholeClearValue();
    }

    private static int getWholeClearValue() {
        return 10;
    }

    public int calculateSum(DownedPin additionalPitch) {
        return pin + additionalPitch.pin;
    }

    public String getDescriptionForm() {
        return convertPin(pin);
    }

    public String getDescriptionForm(DownedPin additionalPitch) {
        if (isSpare(additionalPitch)) {
            return convertPin(pin) + " | /";
        }

        return convertPin(pin) + " | " + convertPin(additionalPitch.pin);
    }

    private String convertPin(int number) {
        if (number == 10) {
            return "X";
        }

        if (number == 0) {
            return "-";
        }

        return Integer.toString(number);
    }
}
