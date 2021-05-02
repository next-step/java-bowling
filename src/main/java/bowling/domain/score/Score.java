package bowling.domain.score;

import java.util.Objects;

public final class Score {

    private static final int CALCULABLE_COUNT = 0;

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
        return null;
    }

    public static Score spare() {
        return null;
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
