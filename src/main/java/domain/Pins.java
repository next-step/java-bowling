package domain;

import java.util.Objects;

public class Pins {

    public final static Pins EMPTY = Pins.of(0);
    public final static Pins ALL = Pins.of(10);

    private final static int MAX_PINS = 10;
    private final int hit;

    private Pins(Integer knockDown) {
        verifyPins(knockDown);
        this.hit = knockDown;
    }

    public static Pins of(Integer knockDown) {
        return new Pins(knockDown);
    }

    private void verifyPins(int knockDown) {
        if (knockDown > MAX_PINS) {
            throw new IllegalArgumentException("최대 10보다 클 수 업습니다.  :" + knockDown);
        }
    }

    public Pins add(Pins target) {
        return Pins.of(this.hit + target.hit);
    }

    @Override
    public String toString() {
        return String.valueOf(hit);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pins pins = (Pins) o;
        return hit == pins.hit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(hit);
    }

    public int value() {
        return hit;
    }
}
