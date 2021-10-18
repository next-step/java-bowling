package step4.domain;

import java.util.Objects;
import step4.exception.MinimumTurnExcpetion;

public class Score {
    private int score;
    private int left;

    public Score(int score, int left) {
        this.score = score;
        this.left = left;
        checkValidLeft(left);
    }

    public Score throwBowl(int falledPins) {
        checkValidLeft(left);
        return new Score(this.score + falledPins, left - 1);

    }

    private void checkValidLeft(int left) {
        if (left < 0) {
            throw new MinimumTurnExcpetion();
        }
    }

    public int getScore() {
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
