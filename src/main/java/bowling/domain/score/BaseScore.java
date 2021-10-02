package bowling.domain.score;

import bowling.domain.frame.Frame;

import java.util.Optional;

import static bowling.common.Pin.MAX;
import static bowling.common.Pin.NO_POINT;

public abstract class BaseScore implements Score {

    protected static int NONE = -1;

    private final int first;

    private final int second;

    private int total;

    protected BaseScore(int first, int second, int total) {
        this.first = first;
        this.second = second;
        this.total = total;
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

    public int getTotal() {
        return total;
    }

    public int calculateWith(Frame now) {
        if (hasTotalScore()) {
            return total;
        }
        int base = baseScoreFrom(now.prev());
        if (base == NONE) {
            return NONE;
        }
        total = calculateWith(base, now);
        return total;
    }

    protected abstract int calculateWith(int base, Frame now);

    protected abstract void validateCombinedScores(int scores);

    private boolean hasTotalScore() {
        return total != NONE;
    }

    private int baseScoreFrom(Frame prev) {
        return Optional.ofNullable(prev).map(Frame::getTotalScore).orElse(0);
    }

}
