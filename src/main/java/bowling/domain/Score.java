package bowling.domain;

public class Score {

    private int score;

    private int remainingCount;

    public Score(int remainingCount) {
        this(0, remainingCount);
    }

    public Score(int score, int remainingCount) {
        this.score = score;
        this.remainingCount = remainingCount;
    }

    public static Score undefined() {
        return new Score(0, -1);
    }

    public static Score of(int count, int bonusCount) {
        return new Score(count, bonusCount);
    }

    public int score() {
        return score;
    }

    public int remainingCount() {
        return remainingCount;
    }

    public boolean isDoneCalculating() {
        return remainingCount == 0;
    }

    public Score calculatedScore(int count) {
        return new Score(score + count, remainingCount - 1);
    }

    public int scoreInInt() {
        return score;
    }

    public boolean isUndefined() {
       return remainingCount == -1;
    }
}
