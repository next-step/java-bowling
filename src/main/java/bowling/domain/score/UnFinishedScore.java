package bowling.domain.score;

public class UnFinishedScore implements Score {

    private final int score;

    private final int remainingCount;

    public UnFinishedScore(int score, int remainingCount) {
        this.score = score;
        this.remainingCount = remainingCount;
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
            return new FinishedScore(this.score + toAdd);
        }
        return new UnFinishedScore(this.score + toAdd, remainingCount - 1);
    }
}
