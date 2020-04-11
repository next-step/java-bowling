package bowling.domain.pin;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Pins {
    public static final int MAX_COUNT = 10;

    private final List<Pin> pins;

    private Pins(final List<Pin> pins) {
        this.pins = Collections.unmodifiableList(pins);
    }

    public static Pins of() {
        return new Pins(createPins());
    }

    private static List<Pin> createPins() {
        return IntStream.range(0, MAX_COUNT)
                        .mapToObj(count -> Pin.of(PinState.STANDING))
                        .collect(Collectors.toList());
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
