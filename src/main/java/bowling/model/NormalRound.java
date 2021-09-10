package bowling.model;

public class NormalRound implements bowling.model.Round {
    private bowling.model.Point point;

    @Override
    public bowling.model.BowlingResult play(int totalPoint, int tryCount) {
        this.point = new bowling.model.Point(totalPoint);
        return bowling.model.BowlingResult.findBowlingResult(point, tryCount);
    }

    @Override
    public bowling.model.Round next(bowling.model.BowlingResult roundResult, int index, int tryCount) {
        if (isBeforeFinalRound(index) && isStrike(roundResult) ||
                isBeforeFinalRound(index) && isSecondTry(tryCount)) {
            return new bowling.model.FinalRound();
        }

        return new NormalRound();
    }

    private boolean isSecondTry(int tryCount) {
        return tryCount == SECOND_TRY;
    }

    private boolean isStrike(bowling.model.BowlingResult roundResult) {
        return roundResult == bowling.model.BowlingResult.STRIKE;
    }

    private boolean isBeforeFinalRound(int index) {
        return index == BEFORE_FINAL_ROUND;
    }

    @Override
    public boolean isSkipNextRound(int tryCount, bowling.model.BowlingResult roundResult, boolean isBonus) {
        if (isStrike(roundResult)) {
            return true;
        }

        return false;
    }

    @Override
    public boolean isBonus(boolean isBonus, bowling.model.BowlingResult roundResult) {
        return false;
    }
}
