package bowling.model;

import java.util.Objects;

public class NormalRound implements Round{
    private Result result;

    public NormalRound() {
        result = new Result(new Miss(), new Miss());
    }

    public NormalRound(Result result) {
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
        return new NormalRound(new Result(beforeResult, new Miss()));
    }

    @Override
    public int calcMaxTryCount() {
        if (result.isStrike()) {
            return -1;
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
        return Objects.equals(result, that.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(result);
    }
}
