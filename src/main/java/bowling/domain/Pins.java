package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Pins {

    private static final int EMPTY = 0;
    private static final int FULL = 10;
    private static final List<Pins> CACHE;

    private final int pinCount;

    static {
        CACHE = new ArrayList<>();
        for (int count = EMPTY; count <= FULL; count++) {
            CACHE.add(new Pins(count));
        }
    }

    public Pins(final int pinCount) {
        this.pinCount = pinCount;
    }

    public static Pins valueOf(final int pinCount) {
        return CACHE.get(pinCount);
    }
}
