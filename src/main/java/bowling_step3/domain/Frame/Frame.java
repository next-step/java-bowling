package bowling_step3.domain.Frame;

import bowling_step3.domain.Score;

public abstract class Frame {

    protected static final int DEFAULT = 0;
    protected static final int MIN_PITCH_COUNT = 2;
    protected static final int MAX_PITCH_COUNT = 3;

    protected Score score;

    public abstract void pitch(int countOfKnockDown);

    public abstract boolean isFinish();

    public abstract Frame next();

    public Integer getScore() {
        return score.getScore();
    }

    public abstract boolean hasScore();

    public abstract void calculateScore(int index, int count);

}

