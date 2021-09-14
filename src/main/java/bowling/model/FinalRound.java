package bowling.model;

import java.util.Objects;

public class FinalRound implements Round{
    private final boolean isBonusRound;
    private Result result;

    public FinalRound() {
        isBonusRound = false;
        result = new Result(new Miss(), new Miss());
    }

    public FinalRound(boolean isBonusRound, Result result) {
        this.isBonusRound = isBonusRound;
        this.result = result;
    }

    @Override
    public GameResult play(int totalPoint, int tryCount) {
        GameResult currentResult = findResult(totalPoint, tryCount);
        this.result = new Result(result.getBefore(), currentResult);
        return currentResult;
    }

    @Override
    public Round next(boolean isBonusRound, GameResult beforeResult) {
        return new FinalRound(isBonusRound, new Result(beforeResult, new Miss()));
    }

    @Override
    public boolean isBonusRound() {
        return isBonusRound;
    }

    @Override
    public boolean isStrike() {
        return false;
    }

    @Override
    public boolean giveBonus() {
        if (isBonusRound) {
            return false;
        }

        if (result.isStrike() || result.isSpare()) {
            return true;
        }

        return false;
    }

    @Override
    public GameResult findResult(int point, int tryCount) {
        if (point == STRIKE) {
            return isStrikeOrSpare(tryCount);
        }

        if (point == GUTTER) {
            return new Gutter();
        }

        return new Miss(point);
    }

    @Override
    public GameResult isStrikeOrSpare(int tryCount) {
        if (tryCount == FIRST_TRY || result.getBefore() instanceof Strike || result.getBefore() instanceof Spare) {
            return new Strike();
        }

        return new Spare();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FinalRound that = (FinalRound) o;
        return isBonusRound == that.isBonusRound && Objects.equals(result, that.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isBonusRound, result);
    }
}
