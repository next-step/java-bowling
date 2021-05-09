package bowling.domain;

import bowling.exception.InvalidPinsSizeException;

import java.util.ArrayList;
import java.util.List;

public class Pins {

    private static final int EMPTY = 0;
    private static final int FULL = 10;
    private static final List<Pins> CACHE;

    private final int count;

    static {
        CACHE = new ArrayList<>();
        for (int count = EMPTY; count <= FULL; count++) {
            CACHE.add(new Pins(count));
        }
    }

    public static final Pins valueOf(final int count) {
        validateSize(count);
        return CACHE.get(count);
    }

    public Pins(final int count) {
        validateSize(count);
        this.count = count;
    }

    private static final void validateSize(final int count) {
        if (count < EMPTY || count > FULL) {
            throw new InvalidPinsSizeException();
        }
    }

    public final int count() {
        return count;
    }
}
