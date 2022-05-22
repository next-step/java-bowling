package bowling.domain;

import java.util.Objects;

public class Pins {

    public static final int DEFAULT_PIN = 10;

    public int pins;

    public Pins() {
        this.pins = DEFAULT_PIN;
    }

    public void hit(int hitCount) {
        if (pins < hitCount || hitCount < 0) {
            throw new IllegalArgumentException("남아있는 핀보다 더 큰 수 또는 음수는 칠 수 없음.");
        }

        pins -= hitCount;
    }

    public int standing() {
        return pins;
    }

    public boolean isHitAll() {
        return standing() == 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Pins pins1 = (Pins) o;
        return pins == pins1.pins;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pins);
    }
}
