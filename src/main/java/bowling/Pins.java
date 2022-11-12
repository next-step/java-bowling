package bowling;

import java.util.HashMap;
import java.util.Map;

public class Pins {

    public static final int MIN_PINS = 0;
    public static final int MAX_PINS = 10;
    private static final Map<Integer, Pins> cache = new HashMap<>();

    static {
        for (int index = MIN_PINS; index <= MAX_PINS; index++) {
            cache.put(index, new Pins(index));
        }
    }

    private final int falledPins;

    private Pins(int falledPins) {
        this.falledPins = falledPins;
    }

    public static Pins from(int falledPins) {
        if (falledPins > MAX_PINS) {
            throw new IllegalArgumentException(
                String.format("볼링 핀은 최대 10을 넘을 수 없습니다. 현재 쓰러진 핀 수는 %d", falledPins));
        }

        if (falledPins < MIN_PINS) {
            throw new IllegalArgumentException(
                String.format("볼링 핀은 최초 0 미만이 될 수 없습니다. 현재 쓰러진 핀 수는 %d", falledPins));
        }
        return cache.get(falledPins);
    }

    public boolean isMax() {
        return falledPins == MAX_PINS;
    }

    public boolean isGutter() {
        return falledPins == MIN_PINS;
    }

    public boolean isMiss() {
        return falledPins < MAX_PINS;
    }

    public Pins addPins(int secondPins) {
        int totalPins = falledPins + secondPins;
        return from(totalPins);
    }

    public int getFalledPins() {
        return falledPins;
    }
}
