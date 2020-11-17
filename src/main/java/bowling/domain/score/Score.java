package bowling.domain.score;

import bowling.domain.point.Point;

public class Score {
    private static final int MISS_LEFT_COUNT = 0;
    private static final int SPARE_SCORE = 10;
    private static final String CALCULATE_ERROR = "점수를 계산할 수 없습니다.";
    private static final int CAN_CALCULATE_COUNT = 0;
    private static final int STRIKE_LEFT_COUNT = 2;
    private static final int STRIKE_SCORE = 10;
    private static final int SPARE_LEFT_COUNT = 1;
    private static final int LEFT_AMOUNT = 1;
    private int score;
    private int leftCount;

    private Score(int score, int leftCount) {
        this.score = score;
        this.leftCount = leftCount;
    }

    public Score roll(int pin) {
        return new Score(score += pin, leftCount - 1);
    }

    public int getScore() {
        if (!canCalculate()) {
            throw new IllegalArgumentException(CALCULATE_ERROR);
        }
        return this.score;
    }

    public boolean canCalculate() {
        return leftCount == CAN_CALCULATE_COUNT;
    }






    public static Score ofMiss(int score) {
        return new Score(score, MISS_LEFT_COUNT);
    }

    public static Score ofStrike() {
        return new Score(SPARE_SCORE, STRIKE_LEFT_COUNT);
    }

    public static Score ofSpare() {
        return new Score(STRIKE_SCORE, SPARE_LEFT_COUNT);
    }

    public Score calculate(int pin) {
        if (leftCount != CAN_CALCULATE_COUNT) {
            return new Score(score += pin, leftCount - 1);
        }
        return this;
    }



    public boolean isRemain() {
        return leftCount != 0;
    }



}
