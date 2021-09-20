package bowling.domain.frames;

import bowling.domain.Score;
import bowling.domain.Scores;
import bowling.domain.exception.FinishFrameException;

import java.util.List;
import java.util.Objects;

public abstract class Frame {

    protected static final int NUMBER_OF_PINS = 10;

    protected Scores scores;
    protected boolean isFinish;

    public Frame() {
        this.scores = new Scores();
    }

    public Frame(final boolean isFinish) {
        this.isFinish = isFinish;
    }

    public void roll(final Score score) {
        checkFinishFrame();
        checkValidNextScore(score);
        this.scores.roll(score);
        if (isEnd()) {
            isFinish = true;
        }
    }

    private void checkFinishFrame() {
        if (this.isFinish) {
            throw new FinishFrameException();
        }
    }

    protected abstract boolean isEnd();

    protected abstract boolean isOverAttempts();

    protected abstract void checkValidNextScore(final Score score);

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
