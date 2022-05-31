package bowling.domain;

import java.util.Objects;

public class Pins {

    public static final int START_PIN_COUNT = 10;
    private static final int MINIMUM_HIT_COUNT = 0;

    private int pins = START_PIN_COUNT;

    public Pins() { }

    public Pins(int hitCount) {
        validateHitCount(hitCount);
        this.pins -= hitCount;
    }

    public boolean isHitAllAfter(int hitCount) {
        validateHitCount(hitCount);
        return this.pins - hitCount == 0;
    }

    public void hit(int hitCount) {
        validateHitCount(hitCount);
        pins -= hitCount;
    }

    private void validateHitCount(int hitCount) {
        if (pins < hitCount || hitCount < MINIMUM_HIT_COUNT) {
            throw new IllegalArgumentException(String.format("남아있는 핀보다 더 큰 수 또는 음수는 칠 수 없음. 현재 남은 핀 수는 %d", pins));
        }
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

    public int hitCount() {
        return START_PIN_COUNT - pins;
    }
}
