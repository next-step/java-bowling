package bowling.domain.score;

public abstract class BaseScore implements Score {

    protected static final int NONE = -1;

    protected static final int MAX = 10;

    private final int first;

    private final int second;

    protected BaseScore(int first, int second) {
        this.first = first;
        this.second = second;
    }

    protected static void validateScore(int score) {
        if (outOfRange(score)) {
            throw new IllegalArgumentException("잘못된 점수를 입력하였습니다.");
        }
    }

    private static boolean outOfRange(int score) {
        return score < 0 || score > MAX;
    }

    public boolean isStrike() {
        return first == MAX;
    }

    public int sum() {
        if (isStrike()) {
            return MAX;
        }
        return first + second;
    }

    public boolean isSpare() {
        return !isStrike() && first + second == MAX;
    }

    public boolean isFirstTryNoPoint() {
        return first == 0;
    }

    public boolean isSecondTryNoPoint() {
        return second == 0;
    }

    public int getFirst() {
        return first;
    }

    public int getSecond() {
        return second;
    }

    protected abstract void validateCombinedScores(int scores);


}
