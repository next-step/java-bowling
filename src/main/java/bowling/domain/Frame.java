package bowling.domain;

import bowling.score.Score;
import bowling.score.Scores;

import java.util.List;

public abstract class Frame {
    public static final int FIRST_FRAME = 1;
    public static final int LAST_FRAME = 10;

    protected int frameNumber;
    protected Scores scores;

    public Frame(int frameNumber, Scores scores) {
        this.frameNumber = frameNumber;
        this.scores = scores;
    }

    @Deprecated
    public static Frame create() {
        return null;
    }

    public abstract void bowl(Score score);

    public abstract boolean canBowl();

    public abstract Frame next();

    public List<Score> getResult() {
        return scores.getResult();
    }

    public boolean getBy(int frameNumber) {
        return this.frameNumber == frameNumber;
    }

    public int getNumber() {
        return frameNumber;
    }
}
