package bowling.domain.frame;

import bowling.domain.Score;

public abstract class DefaultFrame implements Frame {
    public static final int thirdScoreKey = 3;
    public static final int firstScoreKey = 1;
    public static final int secondScoreKey = 2;
    protected Score firstScore;
    protected Score secondScore;

    public Score getFirstScore() {
        return firstScore;
    }
    public Score getSecondScore() {
        return secondScore;
    }

    public boolean isEmpty() {
        return firstScore == null;
    }

    public boolean isStrike() {
        return firstScore.equals(new Score(10));
    }
}
