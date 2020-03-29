package bowling.domain.frame;

import java.util.Objects;

public class Score {
    private int score;
    private int left;

    public Score(int score, int left) {
        this.score = score;
        this.left = left;
    }

    public Score bowl(int fallenPins) {
        return new Score(this.score += fallenPins, this.left - 1);
    }

    public int getScore() {
        if (!isCalculation()) {
            throw new IllegalArgumentException();
        }
        return this.score;
    }

    public boolean isCalculation() {
        return left == 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score1 = (Score) o;
        return score == score1.score &&
                left == score1.left;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score, left);
    }
}
