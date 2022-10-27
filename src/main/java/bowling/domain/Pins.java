package bowling.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

import static java.lang.String.format;

public class Pins {
    private static final int MIN = 0;
    private static final int MAX = 10;
    private static final Map<Integer, Pins> PINS_POOL;

    static {
        Map<Integer, Pins> pinsMap = new HashMap<>();
        IntStream.rangeClosed(MIN, MAX)
                .forEach(fallenPins -> pinsMap.put(fallenPins, new Pins(fallenPins)));
        PINS_POOL = pinsMap;
    }

    private final int fallenPins;

    private Pins(int fallenPins) {
        this.fallenPins = fallenPins;
    }

    public static Pins of(int pins) {
        if (pins < MIN || pins > MAX) {
            throw new IllegalArgumentException(format("쓰러진 핀 수는 %s에서 %s 사이이어야 합니다.", MIN, MAX));
        }

        return PINS_POOL.get(pins);
    }

    public boolean isMax() {
        return fallenPins == MAX;
    }

    public int getFallenPins() {
        return fallenPins;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pins)) return false;

        Pins pins1 = (Pins) o;

        return fallenPins == pins1.fallenPins;
    }

    @Override
    public int hashCode() {
        return fallenPins;
    }
}
