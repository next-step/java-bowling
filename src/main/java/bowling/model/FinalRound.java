package bowling.model;

import java.util.Objects;

public class FinalRound implements Round{
    private static int bonusCount = 1;
    private Result result;

    public FinalRound() {
        result = new Result(new Miss(), new Miss());
    }

    public FinalRound(Result result) {
        this.result = result;
    }

    @Override
    public GameResult play(int totalPoint, int tryCount) {
        GameResult currentResult = findResult(totalPoint, tryCount);
        this.result = new Result(result.getBefore(), currentResult);
        return currentResult;
    }

    @Override
    public Round next(GameResult beforeResult) {
        return new FinalRound(new Result(beforeResult, new Miss()));
    }

    @Override
    public int calcMaxTryCount() {
        System.out.println("bonusCount = " + bonusCount);
        result.printResult();

        if (bonusCount == 0) {
            return 0;
        }

        if (result.isStrike() || result.isSpare()) {
            bonusCount -= 1;
            return 1;
        }

        return 0;
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
        return Objects.equals(result, that.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(result);
    }
}
