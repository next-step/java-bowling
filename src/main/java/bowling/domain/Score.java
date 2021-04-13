package bowling.domain;

public class Score {

    private static final int UNFINISHED_REMAINING_COUNT = -1;

    private final int score;

    private final int remainingCount;

    private Score(int score, int remainingCount) {
        this.score = score;
        this.remainingCount = remainingCount;
    }

    public static Score unfinished(int score) {
        return new Score(score, UNFINISHED_REMAINING_COUNT);
    }

    public static Score of(int score, int remainingCount) {
        return new Score(score, remainingCount);
    }

    public int scoreInInt() {
        return score;
    }

    public int remainingCount() {
        return remainingCount;
    }

    public Score calculatedScore(int toAdd) {
        if(remainingCount <= 0){
            throw new IllegalStateException("더이상 계산 할 수 없습니다.");
        }
        return new Score(this.score + toAdd, remainingCount - 1);
    }

    public boolean isDoneCalculating() {
        return remainingCount <= 0;
    }

    public boolean isUnFinished() {
        return remainingCount == UNFINISHED_REMAINING_COUNT;
    }
}
