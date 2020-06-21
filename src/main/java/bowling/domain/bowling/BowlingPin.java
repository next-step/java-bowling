package bowling.domain.bowling;

import bowling.domain.exception.BowlingBuildingException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;

public class BowlingPin {
    public static final int FIRST_INDEX = 0;
    public static final int LAST_INDEX = 9;
    private static final Map<Integer, BowlingPin> cache = new HashMap<>();

    static {
        IntStream.rangeClosed(FIRST_INDEX, LAST_INDEX)
                .forEach(i -> cache.put(i, new BowlingPin(false)));
    }

    private boolean isStanding;

    private BowlingPin(boolean isStanding) {
        this.isStanding = isStanding;
    }

    public static BowlingPin of(int index) {
        return Optional.ofNullable(cache.get(index))
                .orElseThrow(() -> new BowlingBuildingException(BowlingBuildingException.INVALID_BOWLING_PIN_INDEX));
    }

    public void initiate() {
        this.isStanding = true;
    }

    public void hitByBall() {
        this.isStanding = false;
    }

    public boolean isStanding() {
        return isStanding;
    }
}
