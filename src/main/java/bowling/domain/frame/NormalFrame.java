package bowling.domain.frame;

import bowling.domain.Score;
import bowling.domain.frame.state.State;

public class NormalFrame extends Frame {
    private Frame nextFrame;

    @Override
    public void bowl(int inputScore) {
        State currentState = stateHistory.getLatestState();
        currentState.bowl(inputScore);
        stateHistory.addState(currentState.nextState());
    }

    @Override
    public int getScore() {
        State latestState = stateHistory.getLatestState();
        Score score = latestState.calculateScore();

        if (score.canCalculate()) {
            return score.getScoreTotal();
        }
        return nextFrame.additionalScore(score);
    }

    @Override
    protected int additionalScore(Score score) {
        stateHistory.calculateScore(score);

        if (score.canCalculate()) {
            return score.getScoreTotal();
        }
        return nextFrame.additionalScore(score);
    }

    @Override
    public Frame createFrame(boolean isFinalFrame) {
        if (isFinalFrame) {
            nextFrame = null;
            return null;
        }
        nextFrame = new NormalFrame();
        return nextFrame;
    }
}
