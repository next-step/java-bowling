package bowling.model;

import java.util.Objects;

import static bowling.controller.Main.bowlingResults;

public class FinalRound implements Round{
    private final boolean isBonusRound;
    private Result result;

    public FinalRound() {
        isBonusRound = false;
        result = new Result(BowlingResult.EMPTY, BowlingResult.EMPTY);
    }

    public FinalRound(boolean isBonusRound, Result result) {
        this.isBonusRound = isBonusRound;
        this.result = result;
    }

    @Override
    public BowlingResult play(int totalPoint, int tryCount) {
        BowlingResult currentResult = BowlingResult.findBowlingResult(new Point(totalPoint), tryCount, result.getBefore());
        this.result = new Result(result.getBefore(), currentResult);
        return currentResult;
    }

    @Override
    public Round next(boolean isBonusRound, BowlingResult beforeResult) {
        return new FinalRound(isBonusRound, new Result(beforeResult, BowlingResult.EMPTY));
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
