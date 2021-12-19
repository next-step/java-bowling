package bowling.domain.frame;

import java.util.Objects;

public class Score {

    public static final int POSSIBLE_CALCULATE_LEFT = 0;
    public static final int STRIKE_SCORE = 10;
    public static final int STRIKE_LEFT = 2;
    public static final int SPARE_LEFT = 1;
    public static final int MISS_LEFT = 0;
    public static final int CALCULATE_LEFT_VALUE_WITH_ADD_SCORE = 1;
    public static final int UN_SCORE = -1;
    public static final int UN_SCORE_LEFT = 0;

    private final int value;
    private final int left;

    private Score(int value, int left) {
        this.value = value;
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

    public static Score noScore() {
        return of(UN_SCORE, UN_SCORE_LEFT);
    }

    public static Score of(int value, int left) {
        return new Score(value, left);
    }

    public boolean canCalculateScore() {
        return left == POSSIBLE_CALCULATE_LEFT;
    }

    public Score addScoreByPin(Pin pin) {
        return of(value + pin.getFalledPins(), left - CALCULATE_LEFT_VALUE_WITH_ADD_SCORE);
    }

    public int getValue() {
        return value;
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
        return value == score1.value && left == score1.left;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, left);
    }

    @Override
    public String toString() {
        return "Score{" +
                "score=" + value +
                ", left=" + left +
                '}';
    }
}
