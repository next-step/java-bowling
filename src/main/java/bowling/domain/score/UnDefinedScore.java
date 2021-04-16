package bowling.domain.score;

public class UnDefinedScore implements Score {

    private final int score;

    public UnDefinedScore(int score) {
        this.score = score;
    }

    @Override
    public int currentScore() {
        return score;
    }

    @Override
    public int sumCurrentScore(int scoreToSum) {
        return score + scoreToSum;
    }

    @Override
    public boolean isNecessaryToCalculateMore() {
        return false;
    }

    @Override
    public boolean isFullyCalculated() {
        return false;
    }

    @Override
    public Score calculatedScore(int toAdd) {
        throw new IllegalStateException("끝나지 않은 상태의 결과값은 추가 계산할 수 없습니다.");
    }
}
