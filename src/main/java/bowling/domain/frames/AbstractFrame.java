package bowling.domain.frames;

import bowling.domain.Score;
import bowling.domain.Scores;
import bowling.domain.exception.FinishFrameException;
import bowling.domain.exception.IncorrectNumberOfPinsException;

import java.util.List;
import java.util.Objects;

public abstract class AbstractFrame implements Frame {

    private static final int NUMBER_OF_PINS = 10;

    private Scores scores;
    private boolean isFinish;

    public AbstractFrame() {
        this.scores = new Scores();
    }

    public AbstractFrame(final boolean isFinish) {
        this.isFinish = isFinish;
    }

    @Override
    public void roll(final Score score) {
        checkFinishFrame();
        checkValidNextScore(score);
        this.scores.roll(score);
        if (isFinishFrame()) {
            this.isFinish = true;
        }
    }

    private void checkFinishFrame() {
        if (this.isFinish) {
            throw new FinishFrameException();
        }
    }

    protected void checkIncorrectNumberOfPins(final Score score) {
        int nextDownPins = this.downPins() + score.getNumberOfPins();
        if (nextDownPins > NUMBER_OF_PINS || nextDownPins < 0) {
            throw new IncorrectNumberOfPinsException();
        }
    }

    protected int downPins() {
        return this.scores.downPins();
    }

    protected int numberOfRoll() {
        return this.scores.size();
    }

    protected abstract boolean isFinishFrame();

    protected abstract boolean isOverAttempts();

    protected abstract void checkValidNextScore(final Score score);

    @Override
    public boolean isSpare() {
        List<Score> elements = this.scores.elements();
        if (elements.size() < 2) {
            return false;
        }
        Score first = elements.get(elements.size() - 2);
        Score second = elements.get(elements.size() - 1);
        return Score.isSpare(first, second);
    }

    @Override
    public boolean isStrike() {
        Score lastScore = this.scores.elements()
                .stream()
                .reduce((first, second) -> second)
                .orElse(Score.ZERO);
        return Score.isStrike(lastScore);
    }

    @Override
    public Scores getScores() {
        return this.scores;
    }

    @Override
    public boolean isFinish() {
        return this.isFinish;
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
        AbstractFrame abstractFrame = (AbstractFrame) o;
        return isFinish == abstractFrame.isFinish && Objects.equals(scores, abstractFrame.scores);
    }

    @Override
    public int hashCode() {
        return Objects.hash(scores, isFinish);
    }
}
