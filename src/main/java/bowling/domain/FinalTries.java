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

    @Override
    protected Score fetchScore() {
        if(!isOver()) {
            return Score.NOT_SCORED;
        }

        int first = first().get().value();
        int second = second().get().value();

        if(isThirdNotThrown()) {
            return new Score(first().get().plus(second().get()).value(), 0);
        }

        int third = third().get().value();

        return new Score(first + second + third, 0);
    }
}
