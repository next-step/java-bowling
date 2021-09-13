package step3;

import java.util.Objects;
import step3.exceptions.CannotCalculateExceptions;

public class Score {
    private int score;
    private int left;

    public Score(int score, int left) {
        this.score = score;
        this.left = left;
    }

    public Score bowl(int falledPins) {
        return new Score(score + falledPins, left - 1);
    }

    public int getScore() {
        if (!canCalculateScore()) {
            throw new CannotCalculateExceptions();
        }
        return score;
    }

    public boolean canCalculateScore() {
        return left == 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Score score1 = (Score) o;
        return score == score1.score && left == score1.left;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score, left);
    }
}
