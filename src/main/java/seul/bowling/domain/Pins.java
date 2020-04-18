package seul.bowling.domain;

import lombok.Getter;
import seul.bowling.exception.BowlingException;
import seul.bowling.exception.ExceptionType;

import java.util.ArrayList;
import java.util.List;

public class Pins {
    private static final int MAX_PIN_COUNT = 10;
    private static final int DEFAULT_PLAY_COUNT = 2;

    @Getter
    private List<Pin> pins;

    public Pins() {
        this.pins = new ArrayList<>();
    }

    public void addPin(int clearPin, boolean isBonusPlay) {
        if (!isBonusPlay && pins.size() < DEFAULT_PLAY_COUNT) {
            invalidClearPinCount(clearPin);
        }

        pins.add(new Pin(clearPin));
    }

    public boolean allClear() {
        return totalPin() >= MAX_PIN_COUNT;
    }

    public boolean endDefaultPlayCount(boolean isStrike) {
        if (!isStrike)
            return pins.size() > DEFAULT_PLAY_COUNT;

        return pins.size() >= DEFAULT_PLAY_COUNT;
    }

    private int totalPin() {
        return pins.stream()
                .mapToInt(Pin::getPin)
                .sum();
    }

    private void invalidClearPinCount(int clearPin) {
        int totalClearPin = totalPin() + clearPin;

        if (totalClearPin > MAX_PIN_COUNT) {
            throw new BowlingException(ExceptionType.INVALID_CLEAR_PIN_COUNT);
        }
    }
}
