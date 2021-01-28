package bowling.domain.frame;

import bowling.bowlingexception.IllegalPinRangeException;

import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class DownedPin {

    private static final int MINIMUM_COUNTS_PER_CHANCE = 0;
    private static final int MAXIMUM_COUNTS_PER_CHANCE = 10;

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
        return MINIMUM_COUNTS_PER_CHANCE <= number && number <= MAXIMUM_COUNTS_PER_CHANCE;
    }

    public DownedPin fromPreviousPitch(int downedPin) {
        if (!isValid(this.pin + downedPin)) {
            throw new IllegalPinRangeException();
        }
        return cachedPins.get(downedPin);
    }

    private static int getWholeClearValue() {
        return MAXIMUM_COUNTS_PER_CHANCE;
    }

    public boolean isStrike() {
        return pin == MAXIMUM_COUNTS_PER_CHANCE;
    }

    public static int getDownedPinsOnSpare() {
        return getWholeClearValue();
    }

    public static int getDownedPinsOnStrike() {
        return getWholeClearValue();
    }

    public boolean isSpare(DownedPin additionalPitch) {
        return pin + additionalPitch.pin == MAXIMUM_COUNTS_PER_CHANCE;
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
        if (number == MAXIMUM_COUNTS_PER_CHANCE) {
            return "X";
        }

        if (number == MINIMUM_COUNTS_PER_CHANCE) {
            return "-";
        }

        return Integer.toString(number);
    }
}
