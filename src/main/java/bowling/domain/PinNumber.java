package bowling.domain;

public class PinNumber {
    private static final int MINIMUM_NUMBER = 0;
    private static final int MAXIMUM_NUMBER = 10;

    private final int number;

    public PinNumber(int number) {
        this.number = validatePinNumber(number);
    }

    private int validatePinNumber(int number) {
        if (number < MINIMUM_NUMBER || number > MAXIMUM_NUMBER) {
            throw new IllegalArgumentException("Pin number must be between 0 and 10");
        }

        return number;
    }
}
