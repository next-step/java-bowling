package bowling.domain.score;

import bowling.domain.exception.BowlingBuildingException;

import java.util.HashMap;
import java.util.Map;

public class PitchScore {
    private static final Map<Integer, PitchScore> CACHE = new HashMap<>();
    private static final int MINIMUM_SCORE = 0;
    private static final int MAXIMUM_SCORE = 10;

    private final int score;

    private PitchScore(int score) {
        this.score = score;
    }

    public static PitchScore valueOf(int score) {
        validateScore(score);
        return CACHE.computeIfAbsent(score, PitchScore::new);
    }

    private static void validateScore(int score) {
        if (score < MINIMUM_SCORE || score > MAXIMUM_SCORE) {
            throw new BowlingBuildingException(BowlingBuildingException.INVALID_SCORE_RANGE);
        }
    }

    public boolean isMaximumScore() {
        return score == MAXIMUM_SCORE;
    }

    public boolean isMinimumScore() {
        return score == MINIMUM_SCORE;
    }

    public int getScore() {
        return score;
    }
}
