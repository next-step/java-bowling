package bowling.domain.score;

public class InProgressScore extends Score {
    private final int leftCount;
    protected InProgressScore(int score, int leftCount) {
        super(score);
        this.leftCount = leftCount;
    }

    public static InProgressScore init(int score, int leftCount) {
        return new InProgressScore(score, leftCount);
    }
}
