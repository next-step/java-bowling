package bowling.domain.state;

import java.util.ArrayList;
import java.util.List;

public final class Pins {

    private static final List<Pins> CACHE;
    private static final int MINIMUM_COUNT = 0;
    private static final int MAXIMUM_COUNT = 10;

    private final int pinCount;

    static {
        CACHE = new ArrayList<>();
        for (int count = MINIMUM_COUNT; count <= MAXIMUM_COUNT; count++) {
            CACHE.add(new Pins(count));
        }
    }

    private Pins(final int pinCount) {
        this.pinCount = pinCount;
    }

    public static final Pins initialize() {
        return CACHE.get(MAXIMUM_COUNT);
    }

}
