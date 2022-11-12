package bowling;

public class Score {
    public static final int MINIMUM_COUNT = 0;
    public static final int MAXIMUM_COUNT = 10;
    private final int count;

    public Score(int count) {
        validateOutOfRange(count);
        this.count = count;
    }

    private void validateOutOfRange(int count) {
        if (count < MINIMUM_COUNT || count > MAXIMUM_COUNT) {
            throw new BowlingGameException(ErrorMessage.SCORE_OUT_OF_RANGE);
        }
    }
}
