package bowling.domain.score;

import java.util.Objects;

public class Score {
    private static final int SCORE_LIMIT = 30;

    protected final int score;

    protected Score(int score) {
        validate(score);

        this.score = score;
    }

    public static Score from(int score) {
        return new Score(score);
    }

    private void validate(int score) {
        if (score > SCORE_LIMIT) {
            throw new IllegalArgumentException("Score can't over " + SCORE_LIMIT);
        }
    }

    public boolean isCalculable() {
        return false;
    }

    public Score add(Score anotherScore) {
        return new Score(score + anotherScore.score);
    }

    public int toInt() {
        return score;
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
}
