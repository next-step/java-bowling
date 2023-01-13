package bowling.domain;

import java.util.Objects;

public class Score {

    public static final int MAX_INITIAL_SCORE = 10;
    public static final int STRIKE_LEFT_BOWL_COUNT = 2;
    public static final int SPARE_LEFT_BOWL_COUNT = 1;
    public static final int MISS_LEFT_BOWL_COUNT = 0;

    private int currentScore;
    private int leftBowlCount;

    Score(int initialScore, int leftBowlCount) {
        validateInitialScore(initialScore);
        this.currentScore = initialScore;
        this.leftBowlCount = leftBowlCount;
    }

    private static void validateInitialScore(int initialScore) {
        if (initialScore > MAX_INITIAL_SCORE) {
            throw new IllegalArgumentException("생성 시 가능한 최대 점수는 10입니다.");
        }
        if (initialScore < 0) {
            throw new IllegalArgumentException("점수는 0점보다 작을 수 없습니다.");
        }
    }

    public static Score ofStrike() {
        return new Score(MAX_INITIAL_SCORE, STRIKE_LEFT_BOWL_COUNT);
    }

    public static Score ofSpare() {
        return new Score(MAX_INITIAL_SCORE, SPARE_LEFT_BOWL_COUNT);
    }

    public static Score ofMiss(int currentScore) {
        return new Score(currentScore, MISS_LEFT_BOWL_COUNT);
    }

    public void bowl(int fallenPinCount) {
        currentScore += fallenPinCount;
        leftBowlCount--;
    }

    public boolean canCalculateScore() {
        return leftBowlCount == 0;
    }

    public int score() {
        if (canCalculateScore()) {
            return currentScore;
        }
        throw new IllegalStateException("현재는 점수를 계산할 수 없는 상태입니다!");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score = (Score) o;
        return currentScore == score.currentScore && leftBowlCount == score.leftBowlCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(currentScore, leftBowlCount);
    }
}
