package bowling.domain.score;

import bowling.domain.rolling.Rollings;

import java.util.Objects;

public class Score {

    private static final int NONE = -1;
    private static final int REMAINING_STRIKE = 2;
    private static final int REMAINING_SPARE = 1;
    private static final int NOT_NEED_MORE = 0;
    private static final int FINAL_STRIKE_OR_SPARE = 20;
    private static final int MAX = 30;

    private final int value;
    private final int countOfRemaining;

    public Score(int value, int countOfRemaining) {
        validateFixed(countOfRemaining);
        validateScore(value);
        this.value = value;
        this.countOfRemaining = countOfRemaining;
    }

    public static Score ofNone() {
        return new Score(NONE, NOT_NEED_MORE);
    }

    public static Score of(int value) {
        return new Score(value, NOT_NEED_MORE);
    }

    public static Score of(Rollings rollings) {
        return new Score(rollings.sum(), countOfRemaining(rollings));
    }

    private static int countOfRemaining(Rollings rollings) {
        if (rollings.sum() > FINAL_STRIKE_OR_SPARE) {
            return NOT_NEED_MORE;
        }
        if (rollings.isStrike()) {
            return REMAINING_STRIKE;
        }
        if (rollings.isSpare()) {
            return REMAINING_SPARE;
        }
        return rollings.allRolled() ? NOT_NEED_MORE : REMAINING_SPARE;
    }

    public Score plus(int bonus) {
        return new Score(this.value + bonus, this.countOfRemaining - 1);
    }

    public Score plus(Rollings rollings) {
        return new Score(this.value + rollings.sum(), this.countOfRemaining - 1);
    }

    private void validateFixed(int countOfRemaining) {
        if (countOfRemaining < NOT_NEED_MORE) {
            throw new ScoreFixedException();
        }
    }

    private void validateScore(int score) {
        if (score > MAX) {
            throw new ScoreFixedException();
        }
    }

    public boolean isFixed() {
        return countOfRemaining == NOT_NEED_MORE;
    }

    public int value() {
        if (!isFixed()) {
            return NONE;
        }
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score1 = (Score) o;
        return value == score1.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

}
