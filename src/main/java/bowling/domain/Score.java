package bowling.domain;

import java.util.Objects;

public class Score {
    public static final Score STRIKE = new Score(PinCount.MAX, Left.TWO);
    public static final Score SPARE = new Score(PinCount.MAX, Left.ONE);

    private static final int MIN = 0;

    private static final int MAX = 30;

    private final int score;

    private final Left left;

    public Score(final int score, final Left left) {
        validateRange(score);
        this.score = score;
        this.left = left;
    }

    private void validateRange(final int score) {
        if (score < MIN || score > MAX) {
            throw new IllegalArgumentException("Score 값이 범위를 벗어났습니다. score : " + score);
        }
    }

    public Score plusAdditionalPinCount(final PinCount pinCount) {
        return new Score(this.score + pinCount.getValue(), left.play());
    }

    public boolean canCalculate() {
        return left.isFinished();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Score score1 = (Score) o;
        return score == score1.score && Objects.equals(left, score1.left);
    }

    @Override
    public int hashCode() {
        return Objects.hash(score, left);
    }

    @Override
    public String toString() {
        return String.valueOf(score);
    }
}