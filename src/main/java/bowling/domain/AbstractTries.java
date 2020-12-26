package bowling.domain;

import java.util.Optional;

public abstract class AbstractTries {
    private final Tries tries;

    protected AbstractTries(Tries tries) {
        this.tries = tries;
    }

    abstract protected boolean isOver();

    abstract protected Score fetchScore();

    protected boolean isStrike() {
        return tries.isStrike();
    }

    protected boolean isSpare() {
        return tries.isSpare();
    }

    protected void add(int value) {
        tries.add(value);
    }

    protected boolean isFirstNotThrown() {
        return tries.isFirstNotThrown();
    }

    protected boolean isSecondNotThrown() {
        return tries.isSecondNotThrown();
    }

    protected boolean isThirdNotThrown() { return tries.isThirdNotThrown(); };

    protected Optional<Try> first() {
        return tries.first();
    }

    protected Optional<Try> second() {
        return tries.second();
    }

    protected Optional<Try> third() { return tries.third(); }

    protected Score calculatePreviousScore(Score prevScore) {
        if (isFirstNotThrown()) {
            return Score.NOT_SCORED;
        }

        if(hasOneLeftTry(prevScore)) {
            return new Score(prevScore.value() + first().get().value(), 0);
        }

        if(hasTwoLeftTries(prevScore) && isStrike()) {
            return new Score(2 * Try.MAX.value(), 1);
        }

        if(hasTwoLeftTries(prevScore) && isSecondNotThrown()) {
            return Score.NOT_SCORED;
        }

        if(hasTwoLeftTries(prevScore)) {
            return new Score(10 + first().get().value() + second().get().value(), 0);
        }

        return Score.NOT_SCORED;
    }

    private boolean hasOneLeftTry(Score prevScore) {
        return prevScore.getLeftTries() == 1;
    }

    private boolean hasTwoLeftTries(Score prevScore) {
        return prevScore.getLeftTries() == 2;
    }

    protected int size() {
        return tries.size();
    }
}
