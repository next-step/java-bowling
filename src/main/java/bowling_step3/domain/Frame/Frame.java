package bowling_step3.domain.Frame;

import bowling_step3.domain.Score;

public abstract class Frame {

    protected static final int DEFAULT = 0;

    protected static final int MIN_PITCH_COUNT = 2;

    protected static final int MAX_PITCH_COUNT = 3;

    protected Score score;

    abstract void pitch(int countOfKnockDown);

    abstract boolean isFinish();

    abstract Frame next();

    public abstract String getKnockDownExpression();

    abstract int getScore();

    abstract boolean hasScore();

    public abstract void calculateScore(int index, int count);
}

