package bowling.domain;

public class NormalTries{

    private static final int NORMAL_TRIES_MAX_SIZE = 2;
    private final Tries tries;

    private NormalTries(Tries tries) {
        this.tries = tries;
    }

    public static NormalTries init() {
        return new NormalTries(Tries.init());
    }

    public boolean isOver() {
        return tries.isStrike() || tries.size() == NORMAL_TRIES_MAX_SIZE;
    }

    public boolean isStrike() {
        return tries.isStrike();
    }

    public boolean isSpare() {
        return tries.isSpare();
    }

    public void add(int value) {
        tries.add(value);
    }

    public boolean isFirstNotThrown() {
        return tries.isFirstNotThrown();
    }

    public boolean isSecondNotThrown() {
        return tries.isSecondNotThrown();
    }

    public Try first() {
        return tries.first();
    }

    public Try second() {
        return tries.second();
    }
}
