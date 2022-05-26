package bowling.domain;

import java.util.Objects;

public class Score {
    private static final int MIN_SCORE = 0;
    private static final int MAX_COUNT = 2;
    private static final int MIN_COUNT = 0;

    private int score;
    private int leftCount;

    public Score(int score, int leftCount) {
        validateScoreAndCount(score, leftCount);
        this.score = score;
        this.leftCount = leftCount;
    }

    private void validateScoreAndCount(int score, int leftCount) {
        if (score < MIN_SCORE) {
            throw new IllegalArgumentException("[ERROR] 점수는 음수일 수 없습니다.");
        }

        if (leftCount > MAX_COUNT || leftCount < MIN_COUNT) {
            throw new IllegalArgumentException("[ERROR] 남은 횟수는 " + MIN_COUNT + "~" + MAX_COUNT + "사이여야 합니다.");
        }
    }

    public Score bowl(Pins pins) {
        if (leftCount == MIN_COUNT) {
            throw new IllegalArgumentException("[ERROR] 남은 횟수가 " + MIN_COUNT + "이므로 볼링골을 굴릴 수 없습니다.");
        }
        score = pins.sumScore(score);
        leftCount--;
        return this;
    }

    public int score() {
        return score;
    }

    public boolean canCalculate() {
        return leftCount == 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score1 = (Score) o;
        return score == score1.score && leftCount == score1.leftCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score, leftCount);
    }

    @Override
    public String toString() {
        return Integer.toString(score);
    }
}
