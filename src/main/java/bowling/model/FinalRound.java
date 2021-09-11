package bowling.model;

public class FinalRound implements Round {
    private Point point;

    @Override
    public BowlingResult play(int totalPoint, int tryCount, BowlingResult beforeResult) {
        this.point = new Point(totalPoint);
        return BowlingResult.findBowlingResult(point, tryCount, beforeResult);
    }

    @Override
    public Round next(BowlingResult roundResult, int index, int tryCount) {
        return new FinalRound();
    }

    @Override
    public boolean isSkipNextRound(int tryCount, BowlingResult roundResult, boolean isBonus) {
        return false;
    }

    @Override
    public boolean isBonus(boolean isBonus, BowlingResult roundResult) {
        if (roundResult == BowlingResult.STRIKE || roundResult == BowlingResult.SPARE) {
            return true;
        }

        return false;
    }
}