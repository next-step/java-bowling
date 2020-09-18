package bowling.frame;

import bowling.score.Score;
import bowling.score.Scores;

import java.util.List;

public abstract class Frame {

    public static int FIRST_FRAME_NUMBER = 1;
    public static int FINAL_FRAME_NUMBER = 10;

    protected int frameNumber;
    protected Scores scores;

    public Frame(int frameNumber, Scores scores) {
        this.frameNumber = frameNumber;
        this.scores = scores;
    }

    public Frame(int frameNumber) {
        this.frameNumber = frameNumber;
    }

    public abstract boolean pitching();

    public abstract Frame next();

    public abstract boolean isFinal();

    public int getFrameNumber() {
        return frameNumber;
    }

    public List<Score> getScores() {
        return scores.getScores();
    }

}
