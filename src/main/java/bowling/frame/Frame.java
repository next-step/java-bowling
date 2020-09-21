package bowling.frame;

import bowling.score.Score;
import bowling.score.Scores;

import java.util.Objects;

public abstract class Frame {

    public static final int FIRST_FRAME_NUMBER = 1;
    public static final int FINAL_FRAME_NUMBER = 10;
    public static final int FINISH_FRAME_NUMBER = 11;
    public static final int INCREASE_FRAME_NUMBER = 1;

    protected int frameNumber;
    protected Scores scores;

    public Frame(int frameNumber, Scores scores) {
        this.frameNumber = frameNumber;
        this.scores = scores;
    }

    public boolean canPitching() {
        return scores.canPitching();
    }

    public abstract Frame next();

    public boolean isFinal() {
        return this.frameNumber >= FINISH_FRAME_NUMBER;
    }

    public int getFrameNumber() {
        return frameNumber;
    }

    public Scores getScores() {
        return scores;
    }

    public void pitch(String inputScore) {
        scores.add(Score.from(inputScore));
    }

    public boolean isStrikeIgnore() {
        return (getScores().isFirstCount() && scores.getScore().isStrike());
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

}
