package bowling.domain.frames;

import bowling.domain.Score;
import bowling.domain.Scores;
import bowling.domain.exception.FinishFrameException;

public abstract class Frame {

    public static final int NUMBER_OF_PINS = 10;

    protected Scores scores;
    protected boolean isFinish;

    public Frame() {
        this.scores = new Scores();
    }

    public void roll(final Score score) {
        // TODO 공 합산
        checkFinish();
        checkNumberOfPins();
        this.scores.roll(score);
        finish();
    }

    abstract void checkNumberOfPins();

    abstract boolean isPossibleToAttempts();

    abstract void finish();

    private void checkFinish() {
        if (this.isFinish) {
            throw new FinishFrameException();
        }
    }

    public Scores getScores() {
        return scores;
    }

    public boolean isFinish() {
        return isFinish;
    }

    @Override
    public String toString() {
        return "Frame{" +
                "scores=" + scores +
                ", isFinish=" + isFinish +
                '}';
    }
}
