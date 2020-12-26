package bowling_step3.domain.Frame;

import bowling_step3.domain.Score;

public abstract class Frame {

    protected Score score;

    abstract void pitch(int countOfKnockDown);

    abstract boolean isFinish();

    abstract Frame next();

    public abstract String getKnockDownExpression();

    abstract int getScore();

    abstract boolean hasScore();

    public abstract void calculateScore(int index, int count);
}

