package bowling.domain;

import bowling.exception.NormalTriesCannotHaveThirdException;

import java.util.Optional;

public class NormalTries extends AbstractTries{
    private static final int NORMAL_TRIES_MAX_SIZE = 2;

    private NormalTries(Tries tries) {
        super(tries);
    }

    public static NormalTries init() {
        return new NormalTries(Tries.init());
    }

    @Override
    public boolean isOver() {
        return isStrike() || size() == NORMAL_TRIES_MAX_SIZE;
    }

    @Override
    protected boolean isThirdNotThrown() {
        throw new NormalTriesCannotHaveThirdException();
    }

    @Override
    protected Optional<Try> third() {
        throw new NormalTriesCannotHaveThirdException();
    }

    @Override
    protected Score fetchScore() {
        if (isFirstNotThrown()) {
            return Score.NOT_SCORED;
        }

        if (isStrike()) {
            return new Score(Try.MAX.value(), 2);
        }

        if (isSecondNotThrown()) {
            return new Score(first().get().value(), 1);
        }

        if (isSpare()) {
            return new Score(Try.MAX.value(), 1);
        }

        return new Score(first().get().plus(second().get()).value(), 0);
    }
}

