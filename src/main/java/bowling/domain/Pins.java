package bowling.domain;

import java.util.Objects;

public class Pins {

    public static final int START_PIN_COUNT = 10;
    private static final int MINIMUM_HIT_COUNT = 0;

    private int remainedPins = START_PIN_COUNT;

    public Pins() { }

    public Pins(int hitCount) {
        validateHitCount(hitCount);
        remainedPins -= hitCount;
    }

    public boolean isHitAllAfter(int hitCount) {
        validateHitCount(hitCount);
        return remainedPins - hitCount == MINIMUM_HIT_COUNT;
    }

    public void hit(int hitCount) {
        validateHitCount(hitCount);
        remainedPins -= hitCount;
    }

    private void validateHitCount(int hitCount) {
        if (remainedPins < hitCount || hitCount < MINIMUM_HIT_COUNT) {
            throw new IllegalArgumentException(String.format("남아있는 핀보다 더 큰 수 또는 음수는 칠 수 없음. 현재 남은 핀 수는 %d", remainedPins));
        }
    }

    public int standing() {
        return remainedPins;
    }

    public boolean isHitAll() {
        return standing() == MINIMUM_HIT_COUNT;
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
        return remainedPins == pins1.remainedPins;
    }

    @Override
    public int hashCode() {
        return Objects.hash(remainedPins);
    }

    public int hitCount() {
        return START_PIN_COUNT - remainedPins;
    }
}
