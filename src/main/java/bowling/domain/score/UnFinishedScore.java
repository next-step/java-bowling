package bowling.domain.score;

public class UnFinishedScore implements Score {

    private static final int STRIKE_INITIAL_REMAINING_COUNT = 2;

    private static final int SPARE_INITIAL_REMAINING_COUNT = 1;

    private final int score;

    private final int remainingCount;

    private UnFinishedScore(int score, int remainingCount) {
        this.score = score;
        this.remainingCount = remainingCount;
    }

    public static UnFinishedScore ofStrike(int score) {
        return new UnFinishedScore(score, STRIKE_INITIAL_REMAINING_COUNT);
    }

    public static UnFinishedScore ofSpare(int score) {
        return new UnFinishedScore(score, SPARE_INITIAL_REMAINING_COUNT);
    }

    @Override
    public int currentScore() {
        return score;
    }

    @Override
    public boolean isNecessaryToCalculateMore() {
        return true;
    }

    @Override
    public boolean isFullyCalculated() {
        return false;
    }

    @Override
    public Score calculatedScore(int toAdd) {
        if (remainingCount == 0) {
            throw new IllegalStateException("더이상 계산 할 수 없습니다.");
        }
        if (remainingCount == 1) {
            return FinishedScore.of(this.score + toAdd);
        }
        return new UnFinishedScore(this.score + toAdd, remainingCount - 1);
    }
}
