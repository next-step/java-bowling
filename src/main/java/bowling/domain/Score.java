package bowling.domain;

import bowling.exception.InputException;

import java.util.Objects;

public class Score {
    private static int MIN_SCORE = 0;
    private static final String CREATE_SCORE_ERROR = "점수는 " + MIN_SCORE + " 이상의 숫자입니다.";

    private final int score;

    public Score(final int score) {
        if (score < MIN_SCORE) {
            throw new InputException(CREATE_SCORE_ERROR);
        }
        this.score = score;
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
