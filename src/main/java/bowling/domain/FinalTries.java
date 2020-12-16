package bowling.domain;

public class FinalTries{
    private static final int FINAL_TRIES_MAX_SIZE = 3;
    private final Tries tries;

    public FinalTries(Tries tries) {
        this.tries = tries;
    }

    public static FinalTries init() {
        return new FinalTries(Tries.init());
    }

    public boolean isOver() {
        return tries.size() == FINAL_TRIES_MAX_SIZE ||
                tries.size() == FINAL_TRIES_MAX_SIZE - 1 && !isStrike() && !isSpare();
    }

    public void add(int value) {
        tries.add(value);
    }

    public boolean isStrike() {
        return tries.isStrike();
    }

    public boolean isSpare() {
        return tries.isSpare();
    }

    public boolean isFirstNotThrown() {
        return tries.isFirstNotThrown();
    }

    public boolean isSecondNotThrown() {
        return tries.isSecondNotThrown();
    }

    public boolean isThirdNotThrown() {
        return tries.isThirdNotThrown();
    }

    public Try first() {
        return tries.first();
    }

    public Try second() {
        return tries.second();
    }

    public Try third() {
        return tries.third();
    }
}
