package bowling.domain;

import bowling.exception.BusinessException;

import java.util.Objects;

public class Score {
    private static final int STRIKE_LEFT = 2;
    private static final int SPARE_LEFT = 1;
    private static final int ZERO = 0;

    private int score;
    private int leftPitchCount;

    public Score(final int score, final int leftPitchCount) {
        this.score = score;
        this.leftPitchCount = leftPitchCount;
    }

    public static Score ofStrike() {
        return new Score(Pitch.MAXIMUM_COUNT_OF_PINS, STRIKE_LEFT);
    }

    public static Score ofSpare() {
        return new Score(Pitch.MAXIMUM_COUNT_OF_PINS, SPARE_LEFT);
    }

    public static Score of(final int score) {
        return new Score(score, ZERO);
    }

    public static Score cantCalculate() {
        return new Score(0, -1);
    }

    public Score pitch(final int countOfPins) {
        this.score = score + countOfPins;
        this.leftPitchCount = leftPitchCount - 1;
        return new Score(score, leftPitchCount);
    }

    public int getScore() {
        if (!canCalculateScore()) {
            throw new BusinessException("계산 할 수 없습니다.");
        }
        return this.score;
    }

    public boolean canCalculateScore() {
        return leftPitchCount == ZERO;
    }

    public int leftPitchCount() {
        return leftPitchCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score1 = (Score) o;
        return score == score1.score &&
                leftPitchCount == score1.leftPitchCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score, leftPitchCount);
    }

}
