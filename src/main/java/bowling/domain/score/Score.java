package bowling.domain.score;

import bowling.exception.BowlingException;

import java.util.Objects;

public class Score {

    private static final String SCORE_RANGE = "점수의 범위는 0~300점 사이 여야 합니다.";
    private static final String ADD_COUNT_ERR_MESSAGE = "0~2회 까지만 점수 합산이 가능 합니다.";
    private static final int MIN_SCORE = 0;
    private static final int MAX_SCORE = 300;
    private static final int MIN_ADD_COUNT = 0;
    private static final int MAX_ADD_COUNT = 2;
    private static final int MINUS_COUNT = 1;

    private final int score;
    private int addCount;

    public Score() {
        this(MIN_SCORE, 0);
    }

    public Score(int score) {
        validateScoreRange(score);
        this.score = score;
    }

    public Score(int score, int addCount) {
        validateScoreRange(score);
        validateAddCount(addCount);
        this.score = score;
        this.addCount = addCount;
    }

    private void validateAddCount(int addCount) {
        if (addCount < MIN_ADD_COUNT || addCount > MAX_ADD_COUNT) {
            throw new BowlingException(ADD_COUNT_ERR_MESSAGE);
        }
    }

    private void validateScoreRange(int score) {
        if (score < MIN_SCORE || score > MAX_SCORE) {
            throw new BowlingException(SCORE_RANGE);
        }
    }

    public boolean canAddNextScore() {
        return addCount != 0;
    }

    public Score addScore(Score added) {
        return new Score(this.score + added.score, addCount - MINUS_COUNT);
    }

    public int getScore() {
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score1 = (Score) o;
        return score == score1.score &&
                addCount == score1.addCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score, addCount);
    }
}
