package bowling.domain.frame;

import bowling.domain.rolling.Rollings;
import bowling.domain.rolling.State;

import java.util.List;

public abstract class Frame {
    Rollings rollingResults;
    Score score;

    public abstract void rollingBall(int pinCount);

    public boolean isRollable() {
        return rollingResults.isRollingPossible();
    }

    public List<String> getStates() {
        return rollingResults.getStates();
    }

    public Score getScore() {
        return score;
    }

    public boolean isScoreCalculateDone() {
        return score != null && score.isCalculateDone();
    }

    public void calculateInitialScore() {
        int score = rollingResults.calculateScore();

        calculateByState(score);
    }

    private void calculateByState(int score) {
        if (rollingResults.isState(State.STRIKE)) {
            this.score = Score.calculateStrike(score);
            return;
        }

        if (rollingResults.isState(State.SPARE)) {
            this.score = Score.calculateSpare(score);
            return;
        }

        this.score = Score.calculateNormal(score);
    }

    public void calculateScore(Frame lastFrame) {
        int score = lastFrame.score.getScore() + rollingResults.calculateScore();

        calculateByState(score);
    }

    public void addScore(int knockedDownPinCount) {
        this.score = score.calculate(knockedDownPinCount);
    }

    public void addAdditionalScoreOfLastFrame(Frame lastFrame) {
        rollingResults.calculateAdditionalScore(lastFrame.score);
    }
}
