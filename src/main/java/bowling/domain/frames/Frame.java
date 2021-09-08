package bowling.domain.frames;

import bowling.domain.Score;
import bowling.domain.Scores;
import bowling.domain.exception.FinishFrameException;
import bowling.domain.exception.IncorrectNumberOfPinsException;

import java.util.Objects;

public abstract class Frame {

    public static final int NUMBER_OF_PINS = 10;

    protected Scores scores;
    protected boolean isFinish;

    public Frame() {
        this.scores = new Scores();
    }

    public Frame(final boolean isFinish) {
        this.isFinish = isFinish;
    }

    public void roll(final Score score) {
        this.checkFinish();
        this.checkPossibleRoll(score);
        this.scores.roll(score);
        this.finish();
    }

    abstract void checkPossibleRoll(final Score score);

    abstract boolean isPossibleToAttempts();

    abstract void finish();

    public void checkPossibleSecondRoll(final Score score) {
        if (this.scores.size() == 1) {
            int pins = this.scores.knockedDownPins() + score.getNumberOfPins();
            checkIncorrectNumberOfPins(pins);
        }
    }

    private void checkIncorrectNumberOfPins(final int pins) {
        if (isAllStrike()) {
            return;
        }
        if (pins > NUMBER_OF_PINS || pins < 0) {
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

    public boolean isAllStrike() {
        return this.scores.elements()
                .stream()
                .allMatch(Score::isStrike);
    }

    @Override
    public String toString() {
        return "Frame{" +
                "scores=" + scores +
                ", isFinish=" + isFinish +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Frame frame = (Frame) o;
        return isFinish == frame.isFinish && Objects.equals(scores, frame.scores);
    }

    @Override
    public int hashCode() {
        return Objects.hash(scores, isFinish);
    }
}
