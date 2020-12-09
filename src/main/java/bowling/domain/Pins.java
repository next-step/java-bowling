package bowling.domain;

import java.util.Objects;

import static util.Preconditions.checkArgument;

public class Pins {
    private static final int MINIMUM_COUNT = 0;
    private static final int MAXIMUM_COUNT = 10;
    public static final Pins MAX = Pins.of(MAXIMUM_COUNT);

    public static final String INVALID_PIN_COUNT = "쓰러 진 볼링핀의 개수가 유효하지 않습니다.";

    private final int fallenPin;

    private Pins(final int fallenPin) {
        this.fallenPin = fallenPin;
    }

    public static Pins of(final int fallenPin) {
        checkArgument(fallenPin >= MINIMUM_COUNT && fallenPin <= MAXIMUM_COUNT, INVALID_PIN_COUNT);
        return new Pins(fallenPin);
    }

    public Pins sum(final Pins that) {
        return of(this.fallenPin + that.fallenPin);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pins pins = (Pins) o;
        return fallenPin == pins.fallenPin;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fallenPin);
    }
}
