package bowling.domain.core.state;

import java.util.Objects;

public class Score {
    public static final int NOT_CALCULATED = -1;
    public static final Score empty = new Score(0, 0);
    private final int score;
    private final int left;

    public Score(int score, int left) {
        this.score = score;
        this.left = left;
    }

    public static Score empty(){
        return empty;
    }

    public Score sum(int fallenPin) {
        return new Score(score + fallenPin, left - 1);
    }

    public boolean hasNotAttemptLeft() {
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
        return score == score1.score &&
            left == score1.left;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score, left);
    }

    @Override
    public String toString() {
        return "Score [score=" + score + ", left=" + left + "]";
    }

    public int getScore() {
        return score;
    }
}
