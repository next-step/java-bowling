package bowling.domain;

public class FinalTries extends AbstractTries{
    private static final int FINAL_TRIES_MAX_SIZE = 3;

    private FinalTries(Tries tries) {
        super(tries);
    }

    public static FinalTries init() {
        return new FinalTries(Tries.init());
    }

    @Override
    public boolean isOver() {
        return size() == FINAL_TRIES_MAX_SIZE ||
                size() == FINAL_TRIES_MAX_SIZE - 1 && !isStrike() && !isSpare();
    }
}
