package bowling.model;

import java.util.Objects;

import static bowling.controller.Main.bowlingResults;

public class NormalRound implements Round{
    private final boolean isBonusRound;
    private Result result;

    public NormalRound() {
        isBonusRound = false;
        result = new Result(BowlingResult.EMPTY, BowlingResult.EMPTY);
    }

    public NormalRound(boolean isBonusRound, Result result) {
        this.isBonusRound = isBonusRound;
        this.result = result;
    }

    @Override
    public BowlingResult play(int totalPoint, int tryCount) {
        BowlingResult currentResult = BowlingResult.findBowlingResult(new Point(totalPoint), tryCount, result.getBefore());
        System.out.println("currentResult = " + currentResult);
        result = new Result(result.getBefore(), currentResult);
        return currentResult;
    }

    @Override
    public Round next(boolean isBonusRound, BowlingResult beforeResult) {
        return new NormalRound(false, new Result(beforeResult, BowlingResult.EMPTY));
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
