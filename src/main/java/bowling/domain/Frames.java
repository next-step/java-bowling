package bowling.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Frames {
    public static final int MAP_ROUND_TO_INDEX_CONSTANT = 1;

    private static final List<Frame> CACHE;

    static {
        CACHE = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            CACHE.add(new Frame());
        }
    }

    private final String player;

    public Frames(String player) {
        this.player = player;
    }

    public boolean playFrame(final int round, final int numberOfFallenPins) {
        Frame frame = play(round, numberOfFallenPins);
        return frame.isEnd();
    }

    private Frame play(final int round, final int numberOfFallenPins) {
        return CACHE.get(round - MAP_ROUND_TO_INDEX_CONSTANT).play(round, numberOfFallenPins);
    }

    public List<Frame> getGameRecords() {
        return Collections.unmodifiableList(CACHE);
    }

    public String getPlayer() {
        return player;
    }
}
