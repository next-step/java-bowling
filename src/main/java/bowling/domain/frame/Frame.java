package bowling.domain.frame;

import bowling.domain.Score;

public abstract class Frame {
    public static final int thirdScoreKey = 3;
    public static int firstScoreKey = 1;
    public static int secondScoreKey = 2;
    public Score thirdScore;
    public Score firstScore;
    public Score secondScore;

    public abstract void makeScore(Score firstScore, int index);

    public abstract String convert();

    public abstract boolean isSpare(Score scoreA, Score scoreB);

    public abstract boolean isLastFrame();

    public abstract boolean hasDoneFirstPitch();

    public abstract boolean hasDoneSecondPitch();

    public boolean isEmpty() {
        return firstScore == null;
    }

    public boolean isStrike() {
        return firstScore.equals(new Score(10));
    }
}
