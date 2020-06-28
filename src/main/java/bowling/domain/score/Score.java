package bowling.domain.score;

import java.util.Objects;

public class Score {
    private static final int MIN_SCORE = 0;
    private static final int MAX_SCORE = 10;

    private final int score;

    private Score(int score) {
        validate(score);
        this.score = score;
    }

    private void validate(int score) {
        if (score < MIN_SCORE || score > MAX_SCORE) {
            throw new IllegalArgumentException("점수는 0 에서 10 까지의 숫자만 가능합니다.");
        }
    }

    public static Score of(int score) {
        return new Score(score);
    }

    public static int sum(Score firstScore, Score secondScore) {
        return firstScore.score + secondScore.score;
    }

    @Override
    public String toString() {
        if (score == MIN_SCORE) {
            return "-";
        }

        if (score == MAX_SCORE) {
            return "X";
        }

        return Integer.toString(score);
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
