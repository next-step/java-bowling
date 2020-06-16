package bowling.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class BowlingPin {
    private static final Map<Integer, BowlingPin> cache = new HashMap<>();

    static {
        for (int i = 0; i < 10; i++) {
            cache.put(i, new BowlingPin(false));
        }
    }

    private boolean isStanding;

    private BowlingPin(boolean isStanding) {
        this.isStanding = isStanding;
    }

    public static BowlingPin of(int index) {
        return Optional.ofNullable(cache.get(index))
                .orElseThrow(() -> new BowlingBuildingException(BowlingBuildingException.INVALID_PIN_INDEX));
    }
}
