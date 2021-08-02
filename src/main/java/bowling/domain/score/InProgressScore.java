package bowling.domain.score;

public class InProgressScore extends Score {
    private final int leftCount;
    protected InProgressScore(int score, int leftCount) {
        super(score);
        this.leftCount = leftCount;
    }

    public static Object init(int i, int i1) {
        return null;
    }
}
