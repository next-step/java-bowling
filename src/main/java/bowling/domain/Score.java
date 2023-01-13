package bowling.domain;

import java.util.Objects;

public class Score {

    public static final int MAX_INITIAL_SCORE = 10;
    public static final int STRIKE_LEFT_BOWL_COUNT = 2;
    public static final int SPARE_LEFT_BOWL_COUNT = 1;
    public static final int MISS_LEFT_BOWL_COUNT = 0;

    private final int currentScore;
    private final int leftBowlCount;

    public Score(int initialScore, int leftBowlCount) {
        validateInitialScore(initialScore);
        this.currentScore = initialScore;
        this.leftBowlCount = leftBowlCount;
    }

    private static void validateInitialScore(int initialScore) {
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

    public Score bowl(int fallenPinCount) {
        return new Score(currentScore + fallenPinCount, leftBowlCount - 1);
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
