package bowling.model;

import java.util.Objects;

public class Score {
    private static final int ADD_DELIVERY_COUNT_MAX = 2;
    private static final int ADD_DELIVERY_COUNT_MIN = 0;

    private int score;
    private int leftAddScoreCount;

    private Score(int score, int leftAddScoreCount) {
        this.score = score;
        this.leftAddScoreCount = leftAddScoreCount;
    }

    public static Score of(int score, int leftAddDeliveryCount) {
        validateScore(leftAddDeliveryCount);
        return new Score(score, leftAddDeliveryCount);
    }

    public static Score of(int score) {
        return new Score(score, 0);
    }

    private static void validateScore(int leftAddDeliveryCount) {
        if (leftAddDeliveryCount > ADD_DELIVERY_COUNT_MAX) {
            throw new IllegalArgumentException("추가 점수 갯수는 최대 2입니다.");
        }

        if (leftAddDeliveryCount < ADD_DELIVERY_COUNT_MIN) {
            throw new IllegalArgumentException("추가 점수 갯수는 최소 0입니다.");
        }

    }

    public int getScore() {
        return score;
    }

    public void addScore(int additionalScore) {
        if (!isEndCalculate()) {
            score += additionalScore;
            leftAddScoreCount--;
        }

    }

    public boolean isEndCalculate() {
        return leftAddScoreCount == ADD_DELIVERY_COUNT_MIN;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score1 = (Score) o;
        return score == score1.score &&
                leftAddScoreCount == score1.leftAddScoreCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score, leftAddScoreCount);
    }

}
