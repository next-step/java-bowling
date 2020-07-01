package bowling.domain.score;

import bowling.domain.pin.PinCount;
import bowling.exception.CannotCalculateException;

import java.util.Objects;

public class Score {

    public static final int NOT_ALLOCATED = -1;
    public static final int ZERO = 0;
    public static final Score UN_SCORE = new Score(ZERO, NOT_ALLOCATED);

    private final int score;
    private final int extraBonusCount;

    private Score(final int score, final int extraBonusCount) {
        this.score = score;
        this.extraBonusCount = extraBonusCount;
    }

    public static Score valueOf(final int score, final int leftBonusCount) {
        return new Score(score, leftBonusCount);
    }

    public static Score ofStrike() {
        return new Score(PinCount.MAX_COUNT, 2);
    }

    public static Score ofSpare() {
        return new Score(PinCount.MAX_COUNT, 1);
    }

    public static Score ofMiss(final int totalPins) {
        return new Score(totalPins, ZERO);
    }

    public Score add(final Score score) {
        return new Score(this.score + score.score, this.extraBonusCount + score.extraBonusCount);
    }

    public Score sum(final PinCount pinCount) {
        return new Score(this.score + pinCount.getCount(), extraBonusCount - 1);
    }

    public boolean isCalculable() {
        return this.extraBonusCount == ZERO && isAddableScore();
    }

    private boolean isAddableScore() {
        return !this.equals(UN_SCORE);
    }

    public int getScore() {
        validate();
        return this.score;
    }

    private void validate() {
        if (!isCalculable()) {
            throw new CannotCalculateException();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Score)) return false;
        Score score1 = (Score) o;
        return score == score1.score &&
                extraBonusCount == score1.extraBonusCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score, extraBonusCount);
    }
}
