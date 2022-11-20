package bowling.domain.pin;

import bowling.domain.exception.InvalidCountOfPinException;

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

    public boolean isAllPinFallen() {
        return countOfPin == FallenPins.MAX_COUNT_OF_PIN;
    }

    public FallenPins merge(FallenPins fallenPins) {
        return FallenPins.of(countOfPin + fallenPins.getCountOfPin());
    }

    private void validateCountOfPin(int countOfPin) {
        if (countOfPin < 0 || countOfPin > MAX_COUNT_OF_PIN) {
            throw new InvalidCountOfPinException();
        }
    }

}
