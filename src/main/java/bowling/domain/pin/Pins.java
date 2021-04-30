package bowling.domain.pin;

import bowling.domain.frame.FrameStatus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public final class Pins {

    private static final int FIRST_PIN_INDEX = 0;
    private static final int SECOND_PIN_INDEX = 1;
    private static final int THIRD_PIN_INDEX = 2;

    private final List<Pin> pins;

    private Pins() {
        this(new ArrayList<>());
    }

    private Pins(Pin... pins) {
        this(Arrays.asList(pins));
    }

    private Pins(List<Pin> pins) {
        this.pins = pins;
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
        return pins.get(FIRST_PIN_INDEX);
    }

    public Pin secondPin() {
        return pins.get(SECOND_PIN_INDEX);
    }

    public Pin thirdPin() {
        return pins.get(THIRD_PIN_INDEX);
    }

    public FrameStatus frameStatus() {
        return null;
    }

    public Pins knockDownPin(final Pin pin) {
        return null;
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
