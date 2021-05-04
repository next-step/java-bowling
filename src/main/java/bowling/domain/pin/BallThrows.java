package bowling.domain.pin;

import bowling.domain.frame.FrameStatus;
import bowling.exception.PinsCountExceededException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public final class BallThrows {

    private static final int FIRST_PIN_INDEX = 0;
    private static final int SECOND_PIN_INDEX = 1;
    private static final int THIRD_PIN_INDEX = 2;
    private static final int MAX_PIN_COUNT = 3;
    private static final int NORMAL_PINS_SIZE = 2;
    private static final int SECOND_THROW_SIZE = 1;

    private final PinCountValidator pinCountValidator;
    private final List<Pin> pins;

    private BallThrows() {
        this(new ArrayList<>());
    }

    private BallThrows(Pin... pins) {
        this(Arrays.asList(pins));
    }

    private BallThrows(List<Pin> pins) {
        this(PinCountValidator.NORMAL, pins);
    }

    private BallThrows(PinCountValidator pinCountValidator, Pin... pins) {
        this(pinCountValidator, Arrays.asList(pins));
    }

    private BallThrows(PinCountValidator pinCountValidator) {
        this(pinCountValidator, new ArrayList<>());
    }

    private BallThrows(PinCountValidator pinCountValidator, List<Pin> pins) {
        validatePinsCount(pins.size());
        this.pins = pins;
        this.pinCountValidator = pinCountValidator;
    }

    public static BallThrows create() {
        return new BallThrows();
    }

    public static BallThrows ofFinal() {
        return new BallThrows(PinCountValidator.FINAL);
    }

    public static BallThrows ofFinal(Pin... pins) {
        return new BallThrows(PinCountValidator.FINAL, pins);
    }

    public static BallThrows of(Pin... pins) {
        return new BallThrows(pins);
    }

    public static BallThrows from(List<Pin> pins) {
        return new BallThrows(pins);
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

    public boolean isFirstThrow() {
        return pins.isEmpty();
    }

    public boolean isSecondThrow() {
        return pins.size() == SECOND_THROW_SIZE;
    }

    public int throwCount() {
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
        BallThrows ballThrows1 = (BallThrows) o;
        return Objects.equals(pins, ballThrows1.pins);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pins);
    }
}
