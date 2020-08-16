package bowling.domian.frame;

import bowling.domian.frame.exception.ScoreCalculateDoneException;

public class Score {
    private final int score;
    private final int left;

    private Score(int falledPinsCount) {
        this(falledPinsCount, 0);
    }

    private Score(int falledPinsCount, int left) {
        this.score = falledPinsCount;
        this.left = left;
    }

    public static Score strike() {
        return new Score(10, 2);
    }

    public static Score spare() {
        return new Score(10, 1);
    }

    public static Score miss(int falledPinsCount) {
        return new Score(falledPinsCount);
    }

    public boolean isCalculateDone() {
        return left == 0;
    }

    public int getScore() {
        return score;
    }

    public Score additionalBowl(int falledPinsCount) {
        if (isCalculateDone()) {
            throw new ScoreCalculateDoneException();
        }

        return new Score(
                this.score + falledPinsCount,
                this.left - 1);
    }
}
