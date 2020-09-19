package bowling.frame;

import bowling.score.Score;
import bowling.score.Scores;

import java.util.Objects;

public abstract class Frame {

    public static final int FIRST_FRAME_NUMBER = 1;
    public static final int FINAL_FRAME_NUMBER = 10;
    public static final int INCREASE_FRAME_NUMBER = 1;

    protected int frameNumber;
    protected Scores scores;

    public Frame(int frameNumber, Scores scores) {
        this.frameNumber = frameNumber;
        this.scores = scores;
    }

    public boolean canNormalPitching() {
        return scores.canNormalPitching();
    }

    public boolean canFinalPitching() {
        return scores.canFinalPitching();
    }

    public abstract Frame next();

    public abstract boolean isFinal();

    public int getFrameNumber() {
        return frameNumber;
    }

    public void pitch(Score score) {
        scores.add(score);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Frame frame = (Frame) o;
        return frameNumber == frame.frameNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(frameNumber);
    }

    @Override
    public String toString() {
        return frameNumber + " : " + scores;
    }
}
