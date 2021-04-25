package bowling.domain.pin;

import bowling.domain.frame.FrameStatus;
import bowling.exception.TooManyPinCountException;

import java.util.Objects;

public final class NormalPins implements Pins {

    public static final int MAX_PIN_COUNT = 10;

    private final Pin firstPin;
    private final Pin secondPin;

    public NormalPins(Pin firstPin, Pin secondPin) {
        validatePinCount(firstPin, secondPin);
        this.firstPin = firstPin;
        this.secondPin = secondPin;
    }

    private void validatePinCount(Pin firstPin, Pin secondPin) {
        if (firstPin.pinCount() + secondPin.pinCount() > MAX_PIN_COUNT) {
            throw new TooManyPinCountException();
        }
    }

    @Override
    public Pin firstPin() {
        return firstPin;
    }

    @Override
    public Pin secondPin() {
        return secondPin;
    }

    @Override
    public FrameStatus frameStatus() {
        return FrameStatus.of(firstPin, secondPin);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalPins that = (NormalPins) o;
        return Objects.equals(firstPin, that.firstPin) && Objects.equals(secondPin, that.secondPin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstPin, secondPin);
    }
}
