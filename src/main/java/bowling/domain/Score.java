package bowling.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Score {

    private static final int SCORE_MIN = 0;
    private static final int SCORE_MAX = 10;
    public static final Map<Integer, Score> scoreMap = new HashMap<>();

    private final int score;

    static {
        for (int i = SCORE_MIN; i <= SCORE_MAX; i++) {
            scoreMap.put(i, new Score(i));
        }
    }

    private Score(int score) {
        validateRange(score);
        this.score = score;
    }

    public static Score of(int score) {
        validateRange(score);
        return scoreMap.get(score);
    }

    private static void validateRange(int score) {
        if (score < SCORE_MIN || score > SCORE_MAX) {
            throw new IllegalArgumentException("점수 범위는 " + SCORE_MIN + "부터 " + SCORE_MAX + "까지 입니다.");
        }
    }

    public int value() {
        return this.score;
    }

    public boolean isStrike() {
        return this.score == SCORE_MAX;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Score score1 = (Score) o;
        return this.score == score1.score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.score);
    }
}
