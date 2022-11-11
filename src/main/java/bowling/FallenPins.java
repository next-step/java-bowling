package bowling;

import bowling.exception.InvalidCountOfPinException;

public class FallenPins {

    public static final int MAX_COUNT_OF_PIN = 10;
    private final int countOfPin;

    private FallenPins(int countOfPin) {
        validateCountOfPin(countOfPin);
        this.countOfPin = countOfPin;
    }

    public static FallenPins of(int countOfPin) {
        return new FallenPins(countOfPin);
    }

    public int getCountOfPin() {
        return this.countOfPin;
    }

    private void validateCountOfPin(int countOfPin) {
        if (countOfPin < 0 || countOfPin > MAX_COUNT_OF_PIN) {
            throw new InvalidCountOfPinException();
        }
    }


}
