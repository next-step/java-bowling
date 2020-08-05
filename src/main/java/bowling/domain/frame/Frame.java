package bowling.domain.frame;

import bowling.domain.state.State;

import java.util.List;

public abstract class Frame {
    State state;
    Score score;

    public abstract void rollingBall(int knockedDownPinCount);

    public boolean isRollingPossible() {
        return state.isRollingPossible();
    }

    public List<String> getStates() {
        return state.getStates();
    }

    public int getFrameScore() {
        return score.getScore();
    }

    public boolean isScoreCalculateDone() {
        return score != null && score.isCalculateDone();
    }

    public void calculateScore(Frame lastFrame) {
        if (lastFrame == null) {
            this.score = state.calculateScore();
            return;
        }

        this.score = state.calculateScore(lastFrame.getFrameScore());
    }

    public void addScore(int knockedDownPinCount) {
        this.score = score.calculate(knockedDownPinCount);
    }

    public void addAdditionalScoreOfLastFrame(Frame lastFrame) {
        state.calculateAdditionalScore(lastFrame.score);
    }
}
