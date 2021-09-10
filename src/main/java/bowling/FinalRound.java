package bowling;

public class FinalRound implements bowling.model.Round {
    private Point point;

    @Override
    public BowlingResult play(int totalPoint, int tryCount) {
        this.point = new Point(totalPoint);
        return BowlingResult.findBowlingResult(point, tryCount);
    }

    @Override
    public bowling.model.Round next(BowlingResult roundResult, int index, int tryCount) {
        return new FinalRound();
    }

    @Override
    public boolean isSkipNextRound(int tryCount, BowlingResult roundResult, boolean isBonus) {
        if (!isBonus && roundResult == BowlingResult.STRIKE) {
            return true;
        }

        return false;
    }

    @Override
    public boolean isBonus(boolean isBonus, BowlingResult roundResult) {
        if (isBonus) {
            return false;
        }

        if (roundResult == BowlingResult.STRIKE || roundResult == BowlingResult.SPARE) {
            return true;
        }

        return false;
    }
}