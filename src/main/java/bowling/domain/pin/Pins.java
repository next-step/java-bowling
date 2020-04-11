package bowling.domain.pin;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Pins {
    public static final int MAX_COUNT = 10;

    private final List<Pin> pins;

    Pins(final List<Pin> pins) {
        this.pins = Collections.unmodifiableList(pins);
    }

    public long knockOverCount() {
        return pins.stream()
                   .filter(Pin::isKnockOver)
                   .count();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Pins pins1 = (Pins) o;
        return Objects.equals(pins, pins1.pins);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pins);
    }
}
