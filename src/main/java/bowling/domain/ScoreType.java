package bowling.domain;


import java.util.Optional;

public enum ScoreType {
    STRIKE, SPARE, MISS, GUTTER, SECOND;

    public static ScoreType of(int hit, Optional<Integer> prevHit) {
        if (hit == 10) {
            return STRIKE;
        }
        if (prevHit.isPresent() && (prevHit.get() + hit) == 10) {
            return SPARE;
        }
        if (prevHit.isPresent() && (prevHit.get() + hit) == 0) {
            return GUTTER;
        }
        if (prevHit.isPresent() && (prevHit.get() + hit) < 10) { // should add min condition?
            return MISS;
        }
        return SECOND;
    }
}
