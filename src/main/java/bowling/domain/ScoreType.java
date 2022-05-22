package bowling.domain;


import java.util.Optional;

public enum ScoreType {
    STRIKE, SPARE, MISS, GUTTER, SECOND;

    public static ScoreType of(int hit, Optional<Integer> prevHit) {
        if(prevHit.isPresent()) {
            return evaluateSecond(hit + prevHit.get());
        }
        if (hit == 10) {
            return STRIKE;
        }
        return SECOND;
    }

    private static ScoreType evaluateSecond(int totalScore) {
        if (totalScore == 10) {
            return SPARE;
        }
        if (totalScore < 10) {
            return MISS;
        }
        if (totalScore == 0) {
            return GUTTER;
        }
        throw new RuntimeException("unreachable score: " + totalScore);
    }
}
