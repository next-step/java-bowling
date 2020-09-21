package bowling.model.score;

import bowling.model.Result;

import java.util.Objects;

import static bowling.util.ValidationUtils.throwIfNull;

public class Score {
    private static final String UNKNOWN_STRING = "";
    private static final int UNKNOWN_SCORE = -1;
    public static final Score UNKNOWN = new Score(UNKNOWN_SCORE);

    private final int score;

    private Score(int score) {
        this.score = score;
    }

    public static Score of(Result result) {
        if (Result.UNKNOWN.equals(result)) {
            return UNKNOWN;
        }
        return new Score(result.get());
    }

    public static Score of(int score) {
        return new Score(score);
    }

    public int get() {
        return score;
    }

    public Score add(Score target) {
        if (throwIfNull(target).equals(UNKNOWN) || this.equals(UNKNOWN)) {
            return UNKNOWN;
        }

        return new Score(score + target.score);
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

    public String toString() {
        if (UNKNOWN.equals(this)) {
            return UNKNOWN_STRING;
        }
        return Integer.valueOf(score).toString();
    }
}
