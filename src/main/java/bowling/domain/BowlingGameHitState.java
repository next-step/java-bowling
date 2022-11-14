package bowling.domain;

import java.util.List;

public interface BowlingGameHitState {

    BowlingGameHitState GUTTER = new Gutter();
    BowlingGameHitState MISS = new Miss();
    BowlingGameHitState SPARE = new Spare();
    BowlingGameHitState STRIKE = new Strike();

    List<BowlingGameHitState> ALL_STATES = List.of(GUTTER, MISS, SPARE, STRIKE);

    static BowlingGameHitState from(List<Integer> hits) {
        return ALL_STATES.stream()
                .filter(state -> state.identify(hits))
                .findFirst()
                .orElse(null);
    }

    boolean identify(List<Integer> hits);

    default boolean hasScore(BowlingGameFrame currentFrame) {
        if (currentFrame.isOnGoing()) {
            return false;
        }

        int count = 0;
        BowlingGameFrame frame = currentFrame.getNextFrame();
        while (frame != null
                && frame.countHits() > 0
                && count < getNumberOfBonus()) {
            count += frame.countHits();
            frame = frame.getNextFrame();
        }
        return count >= getNumberOfBonus();
    }

    default int calculateScore(BowlingGameFrame currentFrame) {
        return 0;
    }

    default int getNumberOfBonus() {
        return 0;
    }

}
