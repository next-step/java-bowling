package bowling.domain;

public class Score {

    private static final int UNDEFINED_REMAINING_COUNT =  -1;

    private final int score;

    private final int remainingCount;

    private Score(int score, int remainingCount) {
        this.score = score;
        this.remainingCount = remainingCount;
    }

    public static Score undefined() {
        return new Score(0 , UNDEFINED_REMAINING_COUNT);
    }

    public static Score of(int score, int remainingCount) {
        return new Score(score,remainingCount);
    }

    public int scoreInInt() {
        return score;
    }

    public int remainingCount() {
        return remainingCount;
    }

    public Score calculatedScore(int score) {
        return new Score(this.score+score,remainingCount-1);
    }

    public boolean isDoneCalculating() {
        return remainingCount == 0;
    }

    public boolean isUndefined() {
        return remainingCount == UNDEFINED_REMAINING_COUNT && score == 0;
    }

}
