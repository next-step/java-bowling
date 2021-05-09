package bowling.domain;

import bowling.exception.InvalidPinsSizeException;

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

    public static final Pins valueOf(final int pinCount) {
        validateSize(pinCount);
        return CACHE.get(pinCount);
    }

    public Pins(final int pinCount) {
        validateSize(pinCount);
        this.pinCount = pinCount;
    }

    private static final void validateSize(final int pinCount) {
        if (pinCount < EMPTY || pinCount > FULL) {
            throw new InvalidPinsSizeException();
        }
    }


}
