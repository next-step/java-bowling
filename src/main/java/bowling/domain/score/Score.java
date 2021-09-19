package bowling.domain.score;

import java.util.List;

public abstract class Score {

    protected static final int NONE = -1;

    protected static final int MAX = 10;

    protected final int first;

    protected final int second;

    protected Score(int first, int second) {
        this.first = first;
        this.second = second;
    }

    protected static void validateScore(int score) {
        if (outOfRange(score)) {
            throw new IllegalArgumentException("잘못된 점수를 입력하였습니다.");
        }
    }

    protected abstract void validateCombinedScores(int scores);

    public abstract List<Integer> getAll();

    private static boolean outOfRange(int score) {
        return score < 0 || score > MAX;
    }

    public boolean isStrike() {
        return first == MAX;
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


}
