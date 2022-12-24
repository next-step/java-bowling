package bowling.model;

import java.util.Objects;

public class Score {

    private final int score;
    private final int leftCount;

    public Score(int score, int leftCount) {
        this.score = score;
        this.leftCount = leftCount;
    }

    public Score bowl(int score) {
        return new Score(this.score + score, leftCount - 1);
    }

    public boolean canCalculate() {
        return leftCount == 0;
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
