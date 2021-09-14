package bowling.model;

import java.util.Objects;

public class NormalRound implements Round{
    private final boolean isBonusRound;
    private Result result;

    public NormalRound() {
        isBonusRound = false;
        result = new Result(new Miss(), new Miss());
    }

    public NormalRound(boolean isBonusRound, Result result) {
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
        return new NormalRound(false, new Result(beforeResult, new Miss()));
    }

    @Override
    public boolean isBonusRound() {
        return isBonusRound;
    }

    @Override
    public boolean isStrike() {
        return result.isStrike();
    }

    @Override
    public boolean giveBonus() {
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
        if (tryCount == FIRST_TRY) {
            return new Strike();
        }

        return new Spare();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalRound that = (NormalRound) o;
        return isBonusRound == that.isBonusRound && Objects.equals(result, that.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isBonusRound, result);
    }
}
