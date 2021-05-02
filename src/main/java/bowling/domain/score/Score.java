package bowling.domain.score;

import bowling.domain.pin.Pin;

import java.util.Objects;

public final class Score {

    private static final int CALCULABLE_COUNT = 0;
    private static final int STRIKE_LEFT_COUNT = 2;
    private static final int SPARE_LEFT_COUNT = 1;

    private final int score;
    private final int leftCount;

    public Score(int score, int leftCount) {
        this.score = score;
        this.leftCount = leftCount;
    }

    public static Score of(int score, int leftCount) {
        return new Score(score, leftCount);
    }

    public static Score strike() {
        return new Score(Pin.MAX_COUNT, STRIKE_LEFT_COUNT);
    }

    public static Score spare() {
        return new Score(Pin.MAX_COUNT, SPARE_LEFT_COUNT);
    }

    public boolean canCalculate() {
        return leftCount == CALCULABLE_COUNT;
    }

    public int calculate() {
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score1 = (Score) o;
        return score == score1.score && leftCount == score1.leftCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score, leftCount);
    }
}
