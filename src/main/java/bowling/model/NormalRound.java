package bowling.model;

public class NormalRound implements Round {
    private Point point;

    @Override
    public BowlingResult play(int totalPoint, int tryCount, BowlingResult beforeResult) {
        this.point = new Point(totalPoint);
        return BowlingResult.findBowlingResult(point, tryCount, beforeResult);
    }

    @Override
    public Round next(BowlingResult roundResult, int index, int tryCount) {
        if (isBeforeFinalRound(index) && isStrike(roundResult) ||
                isBeforeFinalRound(index) && isSecondTry(tryCount)) {
            return new FinalRound();
        }

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
    public boolean isSkipNextRound(int tryCount, BowlingResult roundResult, boolean isBonus) {
        if (isStrike(roundResult)) {
            return true;
        }

        return false;
    }

    @Override
    public boolean isBonus(boolean isBonus, BowlingResult roundResult) {
        return false;
    }
}
