package bowling.rollresult;

public class OneRollResult implements RollResultType{
    private final int score;

    private OneRollResult(int score) {
        this.score = score;
    }

    public static RollResultType of(int score) {
        if (score == DEFAULT_MAX_SCORE) {
            return Strike.of();
        }
        return new OneRollResult(score);
    }

    public RollResultType next(int nextScore) {
        if (score + nextScore == DEFAULT_MAX_SCORE) {
            return Spare.of(score, nextScore);
        }
        if (score == 0 && nextScore == 0) {
            return Miss.of();
        }
        return Gutter.of(score, nextScore);
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
        return true;
    }
}
