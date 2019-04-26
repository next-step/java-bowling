package com.hyoj.bowling.domain;

import com.hyoj.bowling.domain.status.Gutter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.IntStream;

public class Pins {
    public static final int MIN = 0;
    public static final int MAX = 10;

    private final int count;

    private static final Map<Integer, Pins> instances = new HashMap<>();
    static {
        IntStream.rangeClosed(MIN, MAX)
            .forEach(i -> instances.put(i, new Pins(i)));
    }

    private Pins(int count) {
        this.count = count;
    }

    public static Pins newPins() {
        return instances.get(MAX);
    }

    public Pins next(final Pins pins) {
        if (this.count + pins.count > MAX) {
            throw new IllegalArgumentException("넘어뜨릴 수 있는 핀 개수를 초과함");
        }

        return getInstanceOf(pins.count);
    }

    public static Pins getInstanceOf(final int pinsCount) {
        if (pinsCount < MIN) {
            throw new IllegalArgumentException("핀 개수는 " + MIN + " 보다 커야함");
        }

        if (pinsCount > MAX) {
            throw new IllegalArgumentException("핀 개수는 " + MAX + " 보다 작아야 함");
        }

        return instances.get(pinsCount);
    }

    public static boolean isAllDown(List<Pins> pins) {
        final int pinsCountSum = pins.stream()
            .mapToInt(p -> p.count)
            .sum();

        return pinsCountSum == MAX;
    }

    public static boolean isAllStanding(List<Pins> pins) {
        final int pinsCountSum = pins.stream()
            .mapToInt(p -> p.count)
            .sum();

        return pinsCountSum == MIN;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Pins pins = (Pins) o;
        return count == pins.count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(count);
    }

    @Override
    public String toString() {
        if (count == MIN) {
            return Gutter.MARK;
        }

        return String.valueOf(count);
    }
}
