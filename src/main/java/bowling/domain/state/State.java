package bowling.domain.state;

import bowling.domain.score.Score;
import bowling.domain.score.Scores;

import java.util.List;

public abstract class State {
    public static int MIN_LEFT_TRY = 0;
    protected int leftTry;
    protected Scores scores;

    protected State(int leftTry) {
        this.leftTry = leftTry;
    }

    protected State(int leftTry, Scores scores) {
        this.leftTry = leftTry;
        this.scores = scores;
    }

    public abstract State record(int pins);

    public abstract Score getScore();

    public boolean isFinished() {
        return leftTry == MIN_LEFT_TRY;
    }

    protected void addScore() {
        scores = scores.add(getScore());
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
