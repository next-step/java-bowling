package bowling.domain;

import java.util.Objects;

public class ScoreBonus {
    private static final int BONUS_INIT = -1;
    private static final int MAX = 2;
    private static final int MIN = 0;
    private static final int FIRST_OR_SPARE = 1;
    private static final int DECREASE = -1;

    private final int count;

    private ScoreBonus(int count) {
        checkValidation(count);
        this.count = count;
    }

    private void checkValidation(int count) {
        if (count < BONUS_INIT || count > MAX) {
            throw new IllegalArgumentException("Score Bonus는 -1 ~ 2 사이 정수입니다.");
        }
    }

    public static ScoreBonus of(int count) {
        return new ScoreBonus(count);
    }

    public static ScoreBonus init() {
        return new ScoreBonus(BONUS_INIT);
    }

    public static ScoreBonus display() {
        return new ScoreBonus(MIN);
    }

    public static ScoreBonus oneMore() {
        return new ScoreBonus(FIRST_OR_SPARE);
    }

    public static ScoreBonus max() {
        return new ScoreBonus(MAX);
    }

    public boolean noLeft() {
        return count == MIN;
    }

    public ScoreBonus decrease() {
        return ScoreBonus.of(count + DECREASE);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScoreBonus that = (ScoreBonus) o;
        return count == that.count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(count);
    }
}
