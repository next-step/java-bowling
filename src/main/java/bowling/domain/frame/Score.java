package bowling.domain.frame;

import java.util.Objects;

public class Score {

    public static final int POSSIBLE_CALCULATE_LEFT = 0;
    public static final int STRIKE_SCORE = 10;
    public static final int STRIKE_LEFT = 2;
    public static final int SPARE_LEFT = 1;
    public static final int MISS_LEFT = 0;
    public static final int CALCULATE_LEFT_VALUE_WITH_ADD_SCORE = 1;
    private final int score;
    private final int left;

    private Score(int score, int left) {
        this.score = score;
        this.left = left;
    }

    public static Score strike() {
        return of(STRIKE_SCORE, STRIKE_LEFT);
    }

    public static Score spare() {
        return of(STRIKE_SCORE, SPARE_LEFT);
    }

    public static Score miss(Pin firstPin, Pin secondPin) {
        return of(firstPin.add(secondPin).getFalledPins(), MISS_LEFT);
    }

    public static Score of(int score, int left) {
        return new Score(score, left);
    }

    public boolean canCalculateScore() {
        return left == POSSIBLE_CALCULATE_LEFT;
    }

    public Score addScore(Score addValue) {
        if (canCalculateScore()) {
           
        }
        return of(score + addValue.score, left - CALCULATE_LEFT_VALUE_WITH_ADD_SCORE);
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
