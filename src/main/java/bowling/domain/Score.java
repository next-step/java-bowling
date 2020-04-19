package bowling.domain;

import java.util.Objects;

import bowling.exception.CannotCalculateException;

public class Score {
    private static final int MAX_PINS = 10;
    private static final int DONE = 0;
    private static final int SPARE_LEFT = 1;
    private static final int STRIKE_LEFT = 2;

    private int score;
    private int left;

    public Score(int score, int left) {
        this.score = score;
        this.left = left;
    }

    public Score bowl(int countOfPins) {
        return new Score(score += countOfPins, left - 1);
    }

    public boolean canCalculateScore() {
        return left == DONE;
    }

    public int getScore() {
        if (!canCalculateScore()) {
            throw new CannotCalculateException("아직 계산이 끝나지 않아 스코어를 반환 할 수 없습니다.");
        }
        return score;
    }

    public static Score ofMiss(int felledPins) {
        return new Score(felledPins, 0);
    }

    public static Score ofSpare() {
        return new Score(MAX_PINS, SPARE_LEFT);
    }

    public static Score ofStrike() {
        return new Score(MAX_PINS, STRIKE_LEFT);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (!(o instanceof Score)) { return false; }
        final Score score1 = (Score) o;
        return score == score1.score &&
               left == score1.left;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score, left);
    }

    @Override
    public String toString() {
        return "Score{" +
               "score=" + score +
               ", left=" + left +
               '}';
    }
}
