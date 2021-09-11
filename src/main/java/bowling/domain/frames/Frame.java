package bowling.domain.frames;

import bowling.domain.Score;
import bowling.domain.Scores;
import bowling.domain.exception.FinishFrameException;
import bowling.domain.exception.IncorrectNumberOfPinsException;

import java.util.List;
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
            int pins = this.scores.downPins() + score.getNumberOfPins();
            checkIncorrectNumberOfPins(pins);
        }
    }

    private void checkIncorrectNumberOfPins(final int pins) {
        if (isStrike()) {
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

    public boolean isSpare() {
        List<Score> elements = this.scores.elements();
        if (elements.size() < 2) {
            return false;
        }
        Score first = elements.get(elements.size() - 2);
        Score second = elements.get(elements.size() - 1);
        return Score.isSpare(first, second);
    }

    public boolean isStrike() {
        Score lastScore = this.scores.elements()
                .stream()
                .reduce((first, second) -> second)
                .orElse(Score.ZERO);
        return Score.isStrike(lastScore);
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
