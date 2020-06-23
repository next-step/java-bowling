package bowling.score;

import bowling.exception.BowlingBuildingException;

import java.util.HashMap;
import java.util.Map;

public class Score {
    private static final Map<Integer, Score> CACHE = new HashMap<>();
    private static final int MINIMUM_SCORE = 0;
    private static final int MAXIMUM_SCORE = 10;

    private final int score;

    private Score(int score) {
        this.score = score;
    }

    public static Score valueOf(int score) {
        validateScore(score);
        return CACHE.computeIfAbsent(score, Score::new);
    }

    private static void validateScore(int score) {
        if (score < MINIMUM_SCORE || score > MAXIMUM_SCORE) {
            throw new BowlingBuildingException(BowlingBuildingException.INVALID_SCORE_RANGE);
        }
    }
}
