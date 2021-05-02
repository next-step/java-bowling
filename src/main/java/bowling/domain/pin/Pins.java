package bowling.domain.pin;

import bowling.domain.frame.FrameStatus;
import bowling.exception.PinsCountExceededException;

import java.util.List;
import java.util.Objects;

public abstract class Pins {

    private static final int FIRST_PIN_INDEX = 0;
    private static final int SECOND_PIN_INDEX = 1;
    private static final int THIRD_PIN_INDEX = 2;
    private static final int MAX_PIN_COUNT = 3;

    protected final List<Pin> pins;

    protected Pins(List<Pin> pins) {
        validatePinsCount(pins.size());
        this.pins = pins;
    }

    private void validatePinsCount(final int count) {
        if (count > MAX_PIN_COUNT) {
            throw new PinsCountExceededException();
        }
    }

    public abstract void validatePinCount(Pin pin);

    public Pin firstPin() {
        return pins.get(FIRST_PIN_INDEX);
    }

    public int firstPinCount() {
        return firstPin().pinCount();
    }

    public Pin secondPin() {
        return pins.get(SECOND_PIN_INDEX);
    }

    public int secondPinCount() {
        return secondPin().pinCount();
    }

    public Pin thirdPin() {
        return pins.get(THIRD_PIN_INDEX);
    }

    public FrameStatus frameStatus() {
        return FrameStatus.of(this);
    }

    public boolean isStrike() {
        return frameStatus() == FrameStatus.STRIKE;
    }

    public boolean isSpare() {
        return frameStatus() == FrameStatus.SPARE;
    }

    public boolean isEnded() {
        return frameStatus() != FrameStatus.NOT_ENDED;
    }

    public void knockDownPin(final Pin pin) {
        validatePinsCount(pins.size() + 1);
        pins.add(pin);
    }

    public int totalPinCount() {
        return pins.stream()
                .mapToInt(Pin::pinCount)
                .sum();
    }

    public boolean isEmpty() {
        return pins.isEmpty();
    }

    public int size() {
        return pins.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pins pins1 = (Pins) o;
        return Objects.equals(pins, pins1.pins);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pins);
    }
}
