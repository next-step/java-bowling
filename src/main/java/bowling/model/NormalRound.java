package bowling.model;

import java.util.Objects;

public class NormalRound implements Round {
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
        return new NormalRound();
    }

    private boolean isSecondTry(int tryCount) {
        return tryCount == SECOND_TRY;
    }

    private boolean isStrike(BowlingResult roundResult) {
        return roundResult == BowlingResult.STRIKE;
    }

    private boolean isBeforeFinalRound(int index) {
        return index == BEFORE_FINAL_ROUND;
    }

    @Override
    public boolean isSkipNextRound() {
        if (isStrike(result)) {
            return true;
        }

        return false;
    }

    @Override
    public boolean isBonus() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalRound that = (NormalRound) o;
        return Objects.equals(point, that.point) && result == that.result;
    }

    @Override
    public int hashCode() {
        return Objects.hash(point, result);
    }
}
