package bowling.domain.score;

import static bowling.common.Pin.MAX;
import static bowling.common.Pin.NO_POINT;

public abstract class BaseScore implements Score {
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
        return score < 0 || score > MAX.value();
    }

    public boolean isStrike() {
        return first == MAX.value();
    }

    public int sum() {
        if (isStrike()) {
            return MAX.value();
        }
        return first + second;
    }

    public boolean isSpare() {
        return !isStrike() && first + second == MAX.value();
    }

    public boolean isFirstTryNoPoint() {
        return first == NO_POINT.value();
    }

    public boolean isSecondTryNoPoint() {
        return second == NO_POINT.value();
    }

    public int getFirst() {
        return first;
    }

    public int getSecond() {
        return second;
    }

    protected abstract void validateCombinedScores(int scores);


}
