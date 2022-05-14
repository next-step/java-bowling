package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Game {
    public static final int MAP_ROUND_TO_INDEX_CONSTANT = 1;

    private static final List<Frame> CACHE;

    static {
        CACHE = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            CACHE.add(new Frame());
        }
    }

    public int play(final int round, final int numberOfFallenPins) {
        return CACHE.get(round - MAP_ROUND_TO_INDEX_CONSTANT).play(round, numberOfFallenPins);
    }

    public void record(final int round, Record record) {
        CACHE.get(round - MAP_ROUND_TO_INDEX_CONSTANT).record(record);
    }
}
