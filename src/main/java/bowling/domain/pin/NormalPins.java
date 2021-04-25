package bowling.domain.pin;

import bowling.domain.frame.FrameStatus;

import java.util.Objects;

public final class NormalPins implements Pins {

    private final Pin firstPin;
    private final Pin secondPin;

    public NormalPins(Pin firstPin, Pin secondPin) {
        validatePinCount(firstPin, secondPin);
        this.firstPin = firstPin;
        this.secondPin = secondPin;
    }

    private void validatePinCount(Pin firstPin, Pin secondPin) {
        firstPin.sum(secondPin);
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
