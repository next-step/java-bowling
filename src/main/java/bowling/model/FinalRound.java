package bowling.model;

import java.util.Objects;

public class FinalRound implements Round {
    private Point point;
    private BowlingResult result;

    @Override
    public BowlingResult play(int totalPoint, int tryCount, BowlingResult beforeResult) {
        this.point = new Point(totalPoint);
        this.result = BowlingResult.findBowlingResult(point, tryCount, beforeResult);
        return result;
    }

    @Override
    public Round next() {
        return new FinalRound();
    }

    @Override
    public boolean isSkipNextRound() {
        return false;
    }

    @Override
    public boolean isBonus() {
        if (result == BowlingResult.STRIKE || result == BowlingResult.SPARE) {
            return true;
        }

        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FinalRound that = (FinalRound) o;
        return Objects.equals(point, that.point) && result == that.result;
    }

    @Override
    public int hashCode() {
        return Objects.hash(point, result);
    }
}