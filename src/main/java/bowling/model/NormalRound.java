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
    public State play(Point pinCount, int tryCount) {
        State currentResult = findResult(pinCount, tryCount);
        this.result = new Result(result.getBefore(), currentResult);
        return currentResult;
    }

    @Override
    public Round next(State beforeResult) {
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
    public State findResult(Point pinCount, int tryCount) {
        if (pinCount.isStrike()) {
            return isStrikeOrSpare(tryCount);
        }

        if (pinCount.isGutter()) {
            return new Gutter();
        }

        return new Miss(pinCount);
    }

    @Override
    public State isStrikeOrSpare(int tryCount) {
        if (tryCount == FIRST_TRY) {
            return new Strike();
        }

        return new Spare();
    }

    public boolean isLastRound(int tryCount) {
        if (result.isStrike() || tryCount == 3) {
            return true;
        }

        return false;
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
