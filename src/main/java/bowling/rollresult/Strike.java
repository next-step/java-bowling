package bowling.rollresult;

public class Strike implements RollResultType {
    private static final String INVALID_SCORE = "스트라이크의 값은 10 이상이어야합니다.";
    private final int score;

    private Strike() {
        score = DEFAULT_MAX_SCORE;
    }

    private Strike(int score) {
        this.score = score;
    }

    public static Strike of() {
        return new Strike();
    }

    public static Strike of(int score) {
        valid(score);
        return new Strike(score);
    }

    private static void valid(int score) {
        if (score < DEFAULT_MAX_SCORE) {
            throw new IllegalArgumentException(INVALID_SCORE);
        }
    }

    @Override
    public boolean isStrike() {
        return true;
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
