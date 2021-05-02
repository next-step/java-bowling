package bowling.rollresult;

public class Gutter implements RollResultType {
    private static final String INVALID_SCORE = "해당 값은 거터가 아닙니다. (spare 혹은 strike)";
    private final int firstScore;
    private final int secondScore;

    private Gutter(int firstScore, int secondScore) {
        this.firstScore = firstScore;
        this.secondScore = secondScore;
    }

    public static Gutter of(int firstScore, int secondScore) {
        valid(firstScore, secondScore);
        return new Gutter(firstScore, secondScore);
    }

    private static void valid(int firstScore, int secondScore) {
        if(firstScore + secondScore >= DEFAULT_MAX_SCORE) {
            throw new IllegalArgumentException(INVALID_SCORE);
        }
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
