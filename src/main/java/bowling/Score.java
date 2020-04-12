package bowling;

import java.util.List;
import java.util.Objects;

public class Score {

    public static final int STRIKE_COUNT = 10;

    private final int score;

    private Score(final int score) {
        validate(score);
        this.score = score;
    }

    private static void validate(final int score) {
        if (score < 0 || score > 10) {
            throw new IllegalArgumentException("Score must be greater than zero and lower than 10.");
        }
    }

    private void validateNext(final int score) {
        int remainScores = STRIKE_COUNT - this.score;

        if (remainScores == 0 || remainScores < score) {
            throw new IllegalArgumentException("Sum of first, second pitching score must be greater than zero and lower than 10.");
        }
    }

    public static Score of(final int score) {
        return new Score(score);
    }

    public Score next(final int score) {
        if (this.score == 10) {
            return new Score(score);
        }

        validateNext(score);
        return new Score(score);
    }

    public static int sum(final List<Score> scores) {
        return scores.stream()
                .mapToInt(score -> score.score)
                .sum();
    }

    public boolean isStrike() {
        return this.score == STRIKE_COUNT;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Score)) return false;
        Score score1 = (Score) o;
        return score == score1.score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score);
    }
}
