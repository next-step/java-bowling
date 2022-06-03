package bowling.domain;

import bowling.exception.InvalidBoundHitException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Hit {

    public static final int MIN_NUMBER = 0;
    public static final int MAX_NUMBER = 10;

    private final int value;

    private Hit(int value) {
        if (isInValidBound(value)) {
            throw new InvalidBoundHitException();
        }
        this.value = value;
    }

    private boolean isInValidBound(int number) {
        return number < MIN_NUMBER || number > MAX_NUMBER;
    }

    public static Hit valueOf(int number) {
        Hit cacheHit = HitCache.findByNumber(number);
        if (cacheHit != null) {
            return cacheHit;
        }
        return HitCache.cacheHit(new Hit(number));
    }

    public int toInt() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hit hit = (Hit) o;
        return value == hit.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    private static class HitCache {
        private static final Map<Integer, Hit> hitCache = new HashMap<>();

        private static Hit cacheHit(Hit hit) {
            hitCache.put(hit.toInt(), hit);
            return hit;
        }

        private static Hit findByNumber(int number) {
            return hitCache.get(number);
        }
    }
}
