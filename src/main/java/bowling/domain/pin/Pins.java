package bowling.domain.pin;

import bowling.exception.BowlCountOverThanPinsException;

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
        return new Pins(create());
    }

    public static Pins valueOf(final List<Pin> pins) {
        return new Pins(pins);
    }

    public Pins knockOver(final BowlCount bowlCount) {
        checkKnockOver(bowlCount);
        return pins.stream()
                   .skip(bowlCount.count())
                   .collect(Collectors.collectingAndThen(Collectors.toList(), Pins::new));
    }

    public int standingCount() {
        return pins.size();
    }

    public boolean isStrike() {
        final long count = pins.stream()
                               .filter(Pin::isKnockOver)
                               .count();
        return count == pins.size();
    }

    private static List<Pin> create() {
        return IntStream.range(0, MAX_COUNT)
                        .mapToObj(count -> Pin.of())
                        .collect(Collectors.toList());
    }

    private void checkKnockOver(final BowlCount bowlCount) {
        if (bowlCount.isGreaterThan(standingCount())) {
            throw new BowlCountOverThanPinsException(bowlCount.count());
        }
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
