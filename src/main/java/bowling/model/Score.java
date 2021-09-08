package bowling.model;

import java.util.HashMap;
import java.util.Map;

public class Score {
    private static final int MIN = 0;
    private static final int MAX = 10;
    private static final Map<Integer, Score> SCORES = new HashMap<>();

    private final int score;

    private Score(int score) {
        validateRange(score);

        this.score = score;
    }

    public static Score of(int scoreValue) {
        Score score = SCORES.get(scoreValue);
        if (score != null) {
            return score;
        }

        score = new Score(scoreValue);
        SCORES.put(scoreValue, score);
        return score;
    }

    private void validateRange(int score) {
        if (score < MIN || score > MAX) {
            throw new IllegalArgumentException("볼링 점수는 0점 이상 10점 이하여야 합니다.");
        }
    }

    public int getValue() {
        return score;
    }

    public boolean isMax() {
        return score == MAX;
    }
}
