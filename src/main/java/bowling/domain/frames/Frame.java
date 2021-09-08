package bowling.domain.frames;

import bowling.domain.Score;
import bowling.domain.Scores;
import bowling.domain.exception.FinishFrameException;
import bowling.domain.exception.IncorrectNumberOfPinsException;
import com.sun.tools.javac.util.List;

public abstract class Frame {

    public static final int NUMBER_OF_PINS = 10;

    protected Scores scores;
    protected boolean isFinish;

    public Frame() {
        this.scores = new Scores();
    }

    public void roll(final Score score) {
        checkFinish();
        checkNumberOfPins(score);
        this.scores.roll(score);
        finish();
    }

    abstract void checkNumberOfPins(final Score score);

    abstract boolean isPossibleToAttempts();

    abstract void finish();

    protected void checkPossibleFirstOrSecondRoll(final Score score) {
        if (!List.of(0, 1).contains(this.scores.size())) {
            return;
        }
        int pins = this.scores.knockedDownPins() + score.getNumberOfPins();
        if (pins > 10 || pins < 0) {
            throw new IncorrectNumberOfPinsException();
        }
    }

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
