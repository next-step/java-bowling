package bowling.domain.frame;

import bowling.domain.frame.rolling.Rollings;

import java.util.Objects;

public class Score {

    private static final int NONE = -1;
    private static final int REMAINING_STRIKE = 2;
    private static final int REMAINING_SPARE = 1;
    private static final int NOT_NEED_MORE = 0;

    private final int value;
    private final int countOfRemaining;

    public Score(int value, int countOfRemaining) {
        this.value = value;
        this.countOfRemaining = countOfRemaining;
    }

    public static Score of(int value) {
        return new Score(value, NOT_NEED_MORE);
    }

    public static Score of(Rollings rollings) {
        int countOfRemaining = getCountOfRemaining(rollings);
        return new Score(rollings.sum(), countOfRemaining);
    }

    private static int getCountOfRemaining(Rollings rollings) {
        if (rollings.isStrike()) {
            return REMAINING_STRIKE;
        }
        return rollings.isSpare() ? REMAINING_SPARE : NOT_NEED_MORE;

    }

    public Score plus(int bonus) {
        if (isFixed()) {
            throw new ScoreFixedException();
        }
        return new Score(this.value + bonus, this.countOfRemaining - 1);
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
