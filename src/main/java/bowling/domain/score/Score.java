package bowling.domain.score;

import java.util.Objects;

public class Score {

    private final int score;
    private final int left;

    private Score(int score, int left) {
        this.score = score;
        this.left = left;
    }

    static Score from(int score, int left) {
        return new Score(score, left);
    }

    public static Score strike() {
        return new Score(10, 2);
    }

    public static Score spare() {
        return new Score(10, 1);
    }

    public static Score miss(int pins) {
        return new Score(pins, 0);
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
