package bowling.domain.pin;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

import static java.lang.String.format;

public class FallenPin {
    private static final int MIN = 0;
    private static final int MAX = 10;
    private static final Map<Integer, FallenPin> FALLEN_PIN_POOL;

    static {
        Map<Integer, FallenPin> pinsMap = new HashMap<>();
        IntStream.rangeClosed(MIN, MAX)
                .forEach(fallenPinCount -> pinsMap.put(fallenPinCount, new FallenPin(fallenPinCount)));
        FALLEN_PIN_POOL = pinsMap;
    }

    private final int count;

    private FallenPin(int count) {
        this.count = count;
    }

    public static FallenPin of(int pins) {
        if (pins < MIN || pins > MAX) {
            throw new IllegalArgumentException(format("쓰러진 핀 수는 %s에서 %s 사이이어야 합니다.", MIN, MAX));
        }

        return FALLEN_PIN_POOL.get(pins);
    }

    public boolean isMax() {
        return count == MAX;
    }

    public int add(FallenPin that) {
        return count + that.count;
    }

    public int getCount() {
        return count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FallenPin)) return false;

        FallenPin fallenPin1 = (FallenPin) o;

        return count == fallenPin1.count;
    }

    @Override
    public int hashCode() {
        return count;
    }
}
