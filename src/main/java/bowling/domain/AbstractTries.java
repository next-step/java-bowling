package bowling.domain;

import java.util.Optional;

public abstract class AbstractTries {
    private final Tries tries;

    protected AbstractTries(Tries tries) {
        this.tries = tries;
    }

    abstract protected boolean isOver();

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

    protected int size() {
        return tries.size();
    }
}
