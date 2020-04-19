package bowling.domain.score;

import bowling.exception.BowlingException;

import java.util.Objects;

public class Score {

    private static final String SCORE_RANGE = "점수의 범위는 0~300점 사이 여야 합니다.";
    private static final int MIN_SCORE = 0;
    private static final int MAX_SCORE = 300;

    private final int score;

    public Score() {
        this(MIN_SCORE);
    }

    public Score(int score) {
        validateScoreRange(score);
        this.score = score;
    }

    private void validateScoreRange(int score) {
        if (score < MIN_SCORE || score > MAX_SCORE) {
            throw new BowlingException(SCORE_RANGE);
        }
    }

    public Score addScore(Score added) {
        return new Score(this.score + added.score);
    }

    public int getScore() {
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
