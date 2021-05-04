package bowling.domain;

import bowling.IllegalPinFallException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Pinfall {
    private static final int MAX_PINFALL = 10;
    private static final int MIN_PINFALL = 0;

    private static final Map<Integer, Pinfall> pinfallCache;
    private final int pinfall;

    static {
        pinfallCache = new HashMap<>();
    }

    private Pinfall(int pinfall) {
        if (pinfall > MAX_PINFALL) {
            throw new IllegalPinFallException("넘어진 핀의 개수가 잘못되었습니다");
        }
        this.pinfall = pinfall;
    }

    public static Pinfall createStrike() {
        return create(MAX_PINFALL);
    }

    public static Pinfall createSpare() {
        return create(MAX_PINFALL);
    }

    public static Pinfall createGutter() {
        return create(MIN_PINFALL);
    }

    public static Pinfall create(int pinfall) {
        Pinfall pinfallClass = pinfallCache.getOrDefault(pinfall, new Pinfall(pinfall));
        addPinfallCache(pinfall, pinfallClass);
        return pinfallClass;
    }


    public Pinfall add(Pinfall pinfall) {
        return new Pinfall(this.pinfall + pinfall.pinfall);
    }

    public boolean isStrike() {
        return pinfall == MAX_PINFALL;
    }

    public int number() {
        return pinfall;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pinfall pinfall1 = (Pinfall) o;
        return pinfall == pinfall1.pinfall;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pinfall);
    }

    private static void addPinfallCache(int pinfall, Pinfall pinfallClass) {
        if (!pinfallCache.containsKey(pinfall)) {
            pinfallCache.put(pinfall, pinfallClass);
        }
    }
}
