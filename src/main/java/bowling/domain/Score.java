package bowling.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Score {
    private final int score;
    private static final Map<Integer, Score> scoreCache;

    static {
        scoreCache = new HashMap<>();
    }

    public Score(int score) {
        this.score = score;
    }

    public static Score createNotDetermined() {
        return create(-1);
    }

    public static Score create(int score) {
        Score scoreClass = scoreCache.get(score);
        if (scoreClass == null) {
            scoreClass = new Score(score);
            scoreCache.put(score, scoreClass);
        }
        return scoreClass;
    }

    public Score add(Score score) {
        if (isNotDetermined(this) || isNotDetermined(score)) {
            return Score.createNotDetermined();
        }

        return Score.create(this.score + score.score);
    }

    @Override
    public String toString() {
        if (score == -1) {
            return "";
        }

        return "" + score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score1 = (Score) o;
        return score == score1.score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score);
    }

    private static boolean isNotDetermined(Score score) {
        return score.score == -1;
    }
}
