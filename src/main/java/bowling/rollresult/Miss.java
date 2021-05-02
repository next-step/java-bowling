package bowling.rollresult;

public class Miss implements RollResultType{

    private final int score = DEFAULT_MIN_SCORE;

    public static Miss of() {
        return new Miss();
    }

    @Override
    public boolean isStrike() {
        return false;
    }

    @Override
    public boolean isSpare() {
        return false;
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public RollResultType next(int nextScore) {
        return this;
    }
}
