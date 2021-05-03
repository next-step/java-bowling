package bowling.domain.pin;

import bowling.domain.frame.FrameStatus;
import bowling.exception.PinsCountExceededException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public final class Pins {

    private static final int FIRST_PIN_INDEX = 0;
    private static final int SECOND_PIN_INDEX = 1;
    private static final int THIRD_PIN_INDEX = 2;
    private static final int MAX_PIN_COUNT = 3;
    private static final int NORMAL_PINS_SIZE = 2;

    private final PinCountValidator pinCountValidator;
    private final List<Pin> pins;

    private Pins() {
        this(new ArrayList<>());
    }

    private Pins(Pin... pins) {
        this(Arrays.asList(pins));
    }

    private Pins(List<Pin> pins) {
        this(PinCountValidator.NORMAL, pins);
    }

    private Pins(PinCountValidator pinCountValidator, Pin... pins) {
        this(pinCountValidator, Arrays.asList(pins));
    }

    private Pins(PinCountValidator pinCountValidator) {
        this(pinCountValidator, new ArrayList<>());
    }

    private Pins(PinCountValidator pinCountValidator, List<Pin> pins) {
        validatePinsCount(pins.size());
        this.pins = pins;
        this.pinCountValidator = pinCountValidator;
    }

    public static Pins create() {
        return new Pins();
    }

    public static Pins ofFinal() {
        return new Pins(PinCountValidator.FINAL);
    }

    public static Pins ofFinal(Pin... pins) {
        return new Pins(PinCountValidator.FINAL, pins);
    }

    public static Pins of(Pin... pins) {
        return new Pins(pins);
    }

    public static Pins from(List<Pin> pins) {
        return new Pins(pins);
    }

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
        validatePinCount(pin);
        validatePinsCount(pins.size() + 1);
        pins.add(pin);
    }

    private void validatePinCount(Pin pin) {
        pinCountValidator.validate(this, pin);
    }

    private void validatePinsCount(final int count) {
        if (count > MAX_PIN_COUNT) {
            throw new PinsCountExceededException();
        }
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

    public boolean isLastGameSpare() {
        return isThirdThrow() && firstPin().isMaximum() && !secondPin().isMaximum();
    }

    private boolean isThirdThrow() {
        return pins.size() == NORMAL_PINS_SIZE;
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
