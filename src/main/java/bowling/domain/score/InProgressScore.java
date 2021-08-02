package bowling.domain.score;

public class InProgressScore extends Score {
    private static final int LEFT_COUNT_LIMIT = 2;

    private final int leftCount;

    protected InProgressScore(int score, int leftCount) {
        super(score);

        validate(leftCount);

        this.leftCount = leftCount;
    }

    private void validate(int leftCount) {
        if (leftCount > LEFT_COUNT_LIMIT) {
            throw new IllegalArgumentException("Left count can't exceed " + LEFT_COUNT_LIMIT);
        }
    }

    public static InProgressScore init(int score, int leftCount) {
        return new InProgressScore(score, leftCount);
    }

    @Override
    public boolean isCalculable() {
        return false;
    }

    @Override
    public Score add(Score anotherScore) {
        return null;
    }
}
