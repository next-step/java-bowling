package bowling.domain.value;

import bowling.utils.Preconditions;

import java.util.List;
import java.util.Objects;

public class Pins {
    private final List<Pin> pins;

    private Pins(List<Pin> pins) {
        Preconditions.checkEmpty(pins, "pins는 필수값입니다.");

        this.pins = pins;
    }

    public static Pins from(List<Pin> pins) {
        return new Pins(pins);
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
