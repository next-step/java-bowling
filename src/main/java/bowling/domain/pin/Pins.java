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
    private static final int FINAL_PIN_SIZE = 3;
    private static final int MAX_PIN_COUNT = 3;

    private final List<Pin> pins;

    private Pins() {
        this(new ArrayList<>());
    }

    private Pins(Pin... pins) {
        this(Arrays.asList(pins));
    }

    private Pins(List<Pin> pins) {
        validatePinsCount(pins.size());
        this.pins = pins;
    }

    private void validatePinsCount(final int count) {
        if (count > MAX_PIN_COUNT) {
            throw new PinsCountExceededException();
        }
    }

    public static Pins create() {
        return new Pins();
    }

    public static Pins of(Pin... pins) {
        return new Pins(pins);
    }

    public static Pins from(List<Pin> pins) {
        return new Pins(pins);
    }

    public Pin firstPin() {
        try {
            return pins.get(FIRST_PIN_INDEX);
        } catch (Exception e) {
            return null;
        }
    }

    public Pin secondPin() {
        try {
            return pins.get(SECOND_PIN_INDEX);
        } catch (Exception e) {
            return null;
        }
    }

    public Pin thirdPin() {
        try {
            return pins.get(THIRD_PIN_INDEX);
        } catch (Exception e) {
            return null;
        }
    }

    public FrameStatus frameStatus() {
        if (pins.size() < FINAL_PIN_SIZE) {
            return FrameStatus.of(firstPin(), secondPin());
        }
        return FrameStatus.of(firstPin(), secondPin(), thirdPin());
    }

    public void knockDownPin(final Pin pin) {
        validatePinsCount(pins.size() + 1);
        pins.add(pin);
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
