package bowling.domain.state;

import bowling.domain.pin.Pin;
import bowling.domain.score.Score;
import bowling.domain.score.Scores;
import bowling.domain.state.exception.InvalidLeftTryException;

import java.util.List;

public abstract class State {
    public static int MIN_LEFT_TRY = 0;
    protected int leftTry;
    protected Scores scores;

    protected State(int leftTry, Scores scores) {
        validate(leftTry);
        this.leftTry = leftTry;
        this.scores = scores;
    }

    private void validate(int leftTry) {
        if (leftTry < MIN_LEFT_TRY) {
            throw new InvalidLeftTryException();
        }
    }

    public abstract State record(Pin pins);

    public boolean isFinished() {
        return leftTry == MIN_LEFT_TRY;
    }

    public List<Score> getScores() {
        return scores.getScores();
    }

    public Integer calculate(Integer previousScore, List<Score> nextScores) {
        if (isFinished()) {
            return scores.calculate(previousScore, nextScores);
        }
        return null;
    }

    public Integer calculate(Integer previousFrameScore) {
        if (isFinished()) {
            return scores.calculate(previousFrameScore);
        }
        return null;
    }

    public int sum() {
        return scores.sum();
    }
}
